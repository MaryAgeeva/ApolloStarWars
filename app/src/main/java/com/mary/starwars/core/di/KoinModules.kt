package com.mary.starwars.core.di

import com.mary.starwars.data.apollo.ApolloClientCreator
import com.mary.starwars.data.repository.FilmsRepository
import com.mary.starwars.domain.repository.IFilmsRepository
import com.mary.starwars.domain.use_case.GetFilmUseCase
import com.mary.starwars.domain.use_case.GetFilmsUseCase
import com.mary.starwars.presentation.detail.DetailFragment
import com.mary.starwars.presentation.films.FilmsFragment
import com.mary.starwars.presentation.detail.DetailViewModel
import com.mary.starwars.presentation.films.FilmsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val baseModule = module {
    single { OkHttpClient.Builder().build() }
    single { ApolloClientCreator(get()).apolloClient }

    single<IFilmsRepository> { FilmsRepository(get()) }

    scope(named<FilmsFragment>()) {
        scoped { GetFilmsUseCase(get()) }
        viewModel { FilmsViewModel(get()) }
    }

    scope(named<DetailFragment>()) {
        scoped { GetFilmUseCase(get()) }
        viewModel { (id : String) ->
            DetailViewModel(
                id
            )
        }
    }
}
