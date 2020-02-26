package com.mary.starwars.domain.repository

interface IRepositoryCallback {

    fun getFilm(id: String)

    fun deleteFilm(id: String)

    fun listenForNewFilm()
}