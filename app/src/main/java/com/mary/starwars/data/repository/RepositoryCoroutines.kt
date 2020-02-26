package com.mary.starwars.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.coroutines.toFlow
import com.mary.starwars.DeleteFilmMutation
import com.mary.starwars.GetFilmQuery
import com.mary.starwars.GetFilmsQuery
import com.mary.starwars.WaitForNewFilmSubscription
import com.mary.starwars.data.mappers.toFilm
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.repository.IRepositoryCoroutines
import com.mary.starwars.domain.utils.FilmNotDeletedException
import com.mary.starwars.domain.utils.FilmNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@UseExperimental(ExperimentalCoroutinesApi::class)
class RepositoryCoroutines(
    private val apolloClient: ApolloClient
) : IRepositoryCoroutines {

    override suspend fun getFilm(id: String): Film {
        return apolloClient.query(
            GetFilmQuery.builder()
                .id(id)
                .build()
        ).toDeferred()
        .await()
        .data()?.Film()?.fragments()?.filmFragment()?.toFilm()?: throw FilmNotFoundException()
    }

    override suspend fun getAllFilms(): List<Film> {
        return apolloClient.query(
            GetFilmsQuery()
        ).toDeferred()
        .await()
        .data()?.allFilms()?.map { film ->
            film.fragments().filmFragment().toFilm()
        }?: listOf()
    }

    override suspend fun deleteFilm(id: String) {
        apolloClient.mutate(
            DeleteFilmMutation.builder()
                .id(id)
                .build()
        ).toDeferred()
        .await()
        .data()?.deleteFilm()?.id()?: throw FilmNotDeletedException()
    }

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