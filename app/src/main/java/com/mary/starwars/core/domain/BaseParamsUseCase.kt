package com.mary.starwars.core.domain

import io.reactivex.rxjava3.core.Single

interface BaseParamsUseCase<P, T> {

    operator fun invoke(parameter: P) : Single<T>
}