package com.mary.starwars.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.mary.starwars.DeleteFilmMutation
import com.mary.starwars.GetFilmQuery
import com.mary.starwars.GetFilmsQuery
import com.mary.starwars.WaitForNewFilmSubscription
import com.mary.starwars.data.mappers.toFilm
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.repository.IRepositoryRx
import com.mary.starwars.domain.utils.FilmNotDeletedException
import com.mary.starwars.domain.utils.FilmNotFoundException
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class RepositoryRx(
    private val apolloClient: ApolloClient
) : IRepositoryRx {

    override fun getFilm(id: String): Single<Film> {
        return Rx2Apollo.from(
            apolloClient.query(
                GetFilmQuery.builder()
                    .id(id)
                    .build()
            )).singleElement()
            .toSingle()
            .map { response ->
                return@map response.data()?.Film()?.fragments()?.filmFragment()?.toFilm()?: throw FilmNotFoundException()
            }
    }

    override fun getAllFilms(): Single<List<Film>> {
        return Rx2Apollo.from(
            apolloClient.query(
                GetFilmsQuery()
            )).singleElement()
            .toSingle()
            .map { response ->
                return@map response.data()?.allFilms()?.map { film ->
                    film.fragments().filmFragment().toFilm()
                }?: listOf()
            }
    }

    override fun deleteFilm(id: String): Completable {
        return Rx2Apollo.from(
            apolloClient.mutate(
                DeleteFilmMutation.builder()
                    .id(id)
                    .build()
            )).singleElement()
            .toSingle()
            .flatMapCompletable { response ->
                return@flatMapCompletable response.data()?.deleteFilm()?.let {
                    Completable.complete()
                }?: Completable.error(FilmNotDeletedException())
            }
    }

    override fun listenForNewFilm(): Flowable<Film> {
        return Rx2Apollo.from(
            apolloClient.subscribe(
                WaitForNewFilmSubscription()
            )
        ).map { response ->
            return@map response.data()?.Film()?.node()?.fragments()?.filmFragment()?.toFilm()?: throw FilmNotFoundException()
        }
    }
}