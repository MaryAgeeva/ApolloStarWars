package com.mary.starwars.core.di

import com.mary.starwars.data.apollo.ApolloClientCreator
import com.mary.starwars.data.repository.RepositoryCoroutines
import com.mary.starwars.data.repository.RepositoryRx
import com.mary.starwars.domain.repository.IRepositoryCoroutines
import com.mary.starwars.domain.repository.IRepositoryRx
import okhttp3.OkHttpClient
import org.koin.dsl.module

val baseModule = module {
    single { OkHttpClient.Builder().build() }
    single { ApolloClientCreator(get()).apolloClient }

    single<IRepositoryCoroutines> { RepositoryCoroutines(get()) }
    single<IRepositoryRx> { RepositoryRx(get()) }
}
