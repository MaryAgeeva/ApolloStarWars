package com.mary.starwars.domain.repository

import com.mary.starwars.domain.entity.Film
import io.reactivex.Single

interface IFilmsRepository {

    fun getAllFilms() : Single<List<Film>>

    fun getFilm(id: String) : Single<Film>
}