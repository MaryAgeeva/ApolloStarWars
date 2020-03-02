package com.mary.starwars.data.example_repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.coroutines.toFlow
import com.mary.starwars.DeleteFilmMutation
import com.mary.starwars.GetFilmQuery
import com.mary.starwars.GetFilmsQuery
import com.mary.starwars.WaitForNewFilmSubscription
import com.mary.starwars.data.mappers.toFilm
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.example_repository.IRepositoryCoroutines
import com.mary.starwars.domain.utils.FilmNotDeletedException
import com.mary.starwars.domain.utils.FilmNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Class, created as example, how to use coroutines extensions for Apollo GraphQL Android
 */
@UseExperimental(ExperimentalCoroutinesApi::class)
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
            GetFilmQuery.builder()
                .id(id)
                .build()
        ).toDeferred()
        .await()
        .data()?.Film()?.fragments()?.filmFragment()?.toFilm()?: throw FilmNotFoundException()
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
        ).toDeferred()
        .await()
        .data()?.allFilms()?.map { film ->
            film.fragments().filmFragment().toFilm()
        }?: listOf()
    }

    /**
     * Shows the usage of fun ApolloCall<T>.toDeferred()
     * on mutation calls
     */
    override suspend fun deleteFilm(id: String) {
        apolloClient.mutate(
            DeleteFilmMutation.builder()
                .id(id)
                .build()
        ).toDeferred()
        .await()
        .data()?.deleteFilm()?.id()?: throw FilmNotDeletedException()
    }

    /**
     * Shows the usage of fun ApolloSubscriptionCall<T>.toFlow()
     * on subscription calls -
     * another option is to use fun ApolloSubscriptionCall<T>.toChannel(capacity: Int = Channel.UNLIMITED)
     * on subscription calls
     *
     * Subscription uses GraphQL fragments, so only one Java class will be generated for every fragment
     */
    override fun listenForNewFilm(): Flow<Film> {
        return apolloClient.subscribe(
            WaitForNewFilmSubscription()
        ).toFlow()
        .map { response ->
            return@map response.data()?.Film()?.node()?.fragments()?.filmFragment()?.toFilm()?: throw FilmNotFoundException()
        }
        .flowOn(Dispatchers.IO)
    }
}