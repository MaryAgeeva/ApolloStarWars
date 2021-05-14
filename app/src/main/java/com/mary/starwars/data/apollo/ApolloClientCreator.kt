package com.mary.starwars.data.apollo

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class ApolloClientCreator(
    okHttp: OkHttpClient
) {
    val apolloClient : ApolloClient = ApolloClient.builder()
        .serverUrl(ENDPOINT)
        .okHttpClient(okHttp)
        .build()

    private companion object {
        const val ENDPOINT = "https://swapi-android.herokuapp.com/"
    }
}