package com.mary.starwars.core.domain

import io.reactivex.Single

interface BaseUseCase<T> {

    operator fun invoke() : Single<T>
}