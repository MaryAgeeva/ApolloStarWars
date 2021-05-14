package com.mary.starwars.domain.example_repository

interface IRepositoryCallback {

    fun getFilm(id: String)

    fun getFilms()
}