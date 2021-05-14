package com.mary.starwars.domain.use_case

import com.mary.starwars.core.domain.BaseUseCase
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.domain.repository.IFilmsRepository
import io.reactivex.rxjava3.core.Single

class GetFilmsUseCase(private val repository: IFilmsRepository) : BaseUseCase<List<Film>> {

    override fun invoke(): Single<List<Film>> = repository.getAllFilms()
}