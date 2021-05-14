package com.mary.starwars.domain.use_case

import com.mary.starwars.core.domain.BaseParamsUseCase
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.repository.IFilmsRepository
import io.reactivex.rxjava3.core.Single

class GetFilmUseCase(private val repository: IFilmsRepository) : BaseParamsUseCase<String, Film> {

    override fun invoke(parameter: String): Single<Film> = repository.getFilm(parameter)
}