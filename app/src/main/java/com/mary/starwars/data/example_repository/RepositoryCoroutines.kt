package com.mary.starwars.data.example_repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.mary.starwars.GetFilmQuery
import com.mary.starwars.GetFilmsQuery
import com.mary.starwars.data.mappers.toFilm
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.example_repository.IRepositoryCoroutines
import com.mary.starwars.domain.utils.FilmNotFoundException
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Class, created as example, how to use coroutines extensions for Apollo GraphQL Android
 */
@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryCoroutines(
    private val apolloClient: ApolloClient
) : IRepositoryCoroutines {

    /**
     * Example, shows the usage of fun ApolloCall<T>.toDeferred()
     * on query calls with parameters;
     * another option is to use fun ApolloCall<T>.toChannel(capacity: Int = Channel.UNLIMITED)
     * on query and mutation calls
     *
     * Query is built with GraphQL fragments, so only one Java class will be generated for every fragment
     */
    override suspend fun getFilm(id: String): Film {
        return apolloClient.query(
            GetFilmQuery(id = id)
        )
        .await()
        .data?.film?.fragments?.filmFragment?.toFilm()?: throw FilmNotFoundException()
    }

    /**
     * Shows another usage of fun ApolloCall<T>.toDeferred()
     * on query calls without parameters;
     * another option is to use fun ApolloCall<T>.toChannel(capacity: Int = Channel.UNLIMITED)
     * on query and mutation calls
     *
     * Query is built with GraphQL fragments, so only one Java class will be generated for every fragment
     */
    override suspend fun getAllFilms(): List<Film> {
        return apolloClient.query(
            GetFilmsQuery()
        )
        .await()
        .data?.allFilms?.edges?.mapNotNull { film ->
            film?.node?.fragments?.filmFragment?.toFilm()
        }?: listOf()
    }
}