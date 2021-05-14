package com.mary.starwars.data.example_repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx3.rxMutate
import com.apollographql.apollo.rx3.rxQuery
import com.apollographql.apollo.rx3.rxSubscribe
import com.mary.starwars.DeleteFilmMutation
import com.mary.starwars.GetFilmQuery
import com.mary.starwars.GetFilmsQuery
import com.mary.starwars.WaitForNewFilmSubscription
import com.mary.starwars.data.mappers.toFilm
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.example_repository.IRepositoryRx
import com.mary.starwars.domain.utils.FilmNotDeletedException
import com.mary.starwars.domain.utils.FilmNotFoundException
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Class, created as example, how to work with RxJava2 support for Apollo GraphQL Android
 */
class RepositoryRx(
    private val apolloClient: ApolloClient
) : IRepositoryRx {

    /**
     * Example, how to work with Rx2Apollo class with query calls,
     * passing the parameters to the query
     *
     * Query is built with GraphQL fragments, so only one Java class will be generated for every fragment
     */
    override fun getFilm(id: String): Single<Film> {
        return apolloClient.rxQuery(
                GetFilmQuery(id = id)
            ).singleElement()
            .toSingle()
            .map { response ->
                return@map response.data?.film?.fragments?.filmFragment?.toFilm()?: throw FilmNotFoundException()
            }
    }

    /**
     * Example, how to work with Rx2Apollo class with query calls
     * without parameters
     *
     * Query is built with GraphQL fragments, so only one Java class will be generated for every fragment
     */
    override fun getAllFilms(): Single<List<Film>> {
        return apolloClient.rxQuery(
                GetFilmsQuery()
            ).singleElement()
            .toSingle()
            .map { response ->
                return@map response.data?.allFilms?.map { film ->
                    film.fragments.filmFragment.toFilm()
                }?: listOf()
            }
    }

    /**
     * Example, how to work with Rx2Apollo class with mutation calls
     */
    override fun deleteFilm(id: String): Completable {
        return apolloClient.rxMutate(
                DeleteFilmMutation(id = id)
            ).flatMapCompletable { response ->
                return@flatMapCompletable response.data?.deleteFilm?.let {
                    Completable.complete()
                }?: Completable.error(FilmNotDeletedException())
            }
    }

    /**
     * Example, how to use Rx2Apollo with subscription calls - return type is Flowable<Response<T>> by default
     *
     * Subscription uses GraphQL fragments, so only one Java class will be generated for every fragment
     */
    override fun listenForNewFilm(): Flowable<Film> {
        return apolloClient.rxSubscribe(
                WaitForNewFilmSubscription()
            )
        .map { response ->
            return@map response.data?.film?.node?.fragments?.filmFragment?.toFilm()?: throw FilmNotFoundException()
        }
    }
}