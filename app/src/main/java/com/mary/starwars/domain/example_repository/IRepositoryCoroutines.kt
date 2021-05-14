package com.mary.starwars.domain.example_repository

import com.mary.starwars.domain.entity.Film

interface IRepositoryCoroutines {

    suspend fun getFilm(id: String) : Film

    suspend fun getAllFilms() : List<Film>
}