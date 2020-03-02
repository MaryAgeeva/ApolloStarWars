package com.mary.starwars.domain.example_repository

import com.mary.starwars.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface IRepositoryCoroutines {

    suspend fun getFilm(id: String) : Film

    suspend fun getAllFilms() : List<Film>

    suspend fun deleteFilm(id: String)

    fun listenForNewFilm() : Flow<Film>
}