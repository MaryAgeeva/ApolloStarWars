package com.mary.starwars.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.mary.starwars.GetFilmsQuery
import com.mary.starwars.data.mappers.toFilm
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.repository.IFilmsRepository
import com.mary.starwars.domain.utils.FilmNotFoundException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FilmsRepository(
    private val apolloClient: ApolloClient
) : IFilmsRepository {

    private var filmsList = listOf<Film>()

    override fun getFilm(id: String): Single<Film> {
        return Single.fromCallable {
            filmsList.firstOrNull { it.id == id }?: throw FilmNotFoundException()
        }
    }

    override fun getAllFilms(): Single<List<Film>> {
        return if(filmsList.isEmpty())
             Rx2Apollo.from(
                 apolloClient.query(
                     GetFilmsQuery()
                 )).singleElement()
             .toSingle()
             .map { response ->
                 filmsList = response.data()?.allFilms()?.map { film ->
                     film.fragments().filmFragment().toFilm()
                 }?: listOf()
                 return@map filmsList
             }
             .subscribeOn(Schedulers.io())
        else Single.just(filmsList)
    }
}