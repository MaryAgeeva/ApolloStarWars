package com.mary.starwars.data.apollo

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import okhttp3.OkHttpClient

class ApolloClientCreator(
    okHttp: OkHttpClient
) {
    val apolloClient : ApolloClient = ApolloClient.builder()
        .serverUrl(ENDPOINT)
        .subscriptionTransportFactory(
            WebSocketSubscriptionTransport.Factory(SUBSCRIPTIONS_ENDPOINT, okHttp))
        .okHttpClient(okHttp)
        .build()

    private companion object {
        const val ENDPOINT = "https://swapi.graph.cool/graphql"
        const val SUBSCRIPTIONS_ENDPOINT = "wss://swapi.graph.cool/graphql"
    }
}