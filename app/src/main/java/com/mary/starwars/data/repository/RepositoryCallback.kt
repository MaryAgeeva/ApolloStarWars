package com.mary.starwars.data.repository

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloSubscriptionCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.mary.starwars.DeleteFilmMutation
import com.mary.starwars.GetFilmQuery
import com.mary.starwars.WaitForNewFilmSubscription
import com.mary.starwars.domain.repository.IRepositoryCallback

/***
 * Class, created as example, how to execute Apollo calls out of box,
 * without adding additional dependencies on Apollo RxJava2 and Coroutines support
 */
class RepositoryCallback(
    private val apolloClient: ApolloClient
) : IRepositoryCallback {

    override fun getFilm(id: String) {
        apolloClient.query(
            GetFilmQuery.builder()
                .id(id)
                .build()
        ).enqueue(object : ApolloCall.Callback<GetFilmQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                /* Response to exception */
            }

            override fun onResponse(response: Response<GetFilmQuery.Data>) {
                /* Do something */
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