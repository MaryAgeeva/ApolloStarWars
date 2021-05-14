package com.mary.starwars.core.domain

import io.reactivex.rxjava3.core.Single

interface BaseUseCase<T> {

    operator fun invoke() : Single<T>
}