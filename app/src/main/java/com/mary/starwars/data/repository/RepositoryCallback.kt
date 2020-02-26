package com.mary.starwars.data.repository

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloSubscriptionCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mary.starwars.*
import com.mary.starwars.domain.repository.IRepositoryCallback

/***
 * Class, created as example, how to execute Apollo calls out of box,
 * without adding additional dependencies on Apollo RxJava2 and Coroutines support
 */
class RepositoryCallback(
    private val apolloClient: ApolloClient
) : IRepositoryCallback {

    /***
     * Example shows 2 queries with the same GraphQL type - Film, but without optimization from Android side -
     * 2 different classes will be generated for the same GraphQL type
     */
    override fun getFilm(id: String) {
        apolloClient.query(
            GetFilmNotOptimizedQuery.builder()
                .id(id)
                .build()
        ).enqueue(object : ApolloCall.Callback<GetFilmNotOptimizedQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                /* Response to exception */
            }

            override fun onResponse(response: Response<GetFilmNotOptimizedQuery.Data>) {
                val filmType = response.data()?.Film()?.__typename()
                val filmClassName = response.data()?.Film()?.javaClass?.simpleName
                Log.i("ApolloStarWars", """
                    GetFilmNotOptimizedQuery: film type = $filmType, film Java class = $filmClassName
                """)
                //result: GetFilmNotOptimizedQuery: film type = Film, film Java class = Film
            }
        })
    }

    override fun getFilms() {
        apolloClient.query(
            GetFilmsNotOptimizedQuery()
        ).enqueue(object : ApolloCall.Callback<GetFilmsNotOptimizedQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                /* Response to exception */
            }

            override fun onResponse(response: Response<GetFilmsNotOptimizedQuery.Data>) {
                val filmType = response.data()?.allFilms()?.first()?.__typename()
                val filmClassName = response.data()?.allFilms()?.first()?.javaClass?.simpleName
                Log.i("ApolloStarWars", """
                    GetFilmsNotOptimizedQuery: films type = $filmType, film Java class = $filmClassName
                """)
                //GetFilmsNotOptimizedQuery: films type = Film, film Java class = AllFilm
            }
        })
    }

    override fun deleteFilm(id: String) {
        apolloClient.mutate(
            DeleteFilmMutation.builder()
                .id(id)
                .build()
        ).enqueue(object : ApolloCall.Callback<DeleteFilmMutation.Data>() {
            override fun onFailure(e: ApolloException) {
                /* Response to exception */
            }

            override fun onResponse(response: Response<DeleteFilmMutation.Data>) {
                /* Do something */
            }
        })
    }

    override fun listenForNewFilm() {
        apolloClient.subscribe(
            WaitForNewFilmSubscription()
        ).execute(object : ApolloSubscriptionCall.Callback<WaitForNewFilmSubscription.Data> {
            override fun onFailure(e: ApolloException) {
                /* Response to exception */
            }

            override fun onResponse(response: Response<WaitForNewFilmSubscription.Data>) {
                /* Do something */
            }

            override fun onConnected() {
                /* Do something */
            }

            override fun onTerminated() {
                /* Do something */
            }

            override fun onCompleted() {
                /* Do something */
            }
        })
    }
}