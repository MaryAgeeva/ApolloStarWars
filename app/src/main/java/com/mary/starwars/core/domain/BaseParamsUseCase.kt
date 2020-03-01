package com.mary.starwars.core.domain

import io.reactivex.Single

interface BaseParamsUseCase<P, T> {

    operator fun invoke(parameter: P) : Single<T>
}