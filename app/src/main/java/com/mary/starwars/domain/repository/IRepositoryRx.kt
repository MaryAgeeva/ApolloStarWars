package com.mary.starwars.domain.repository

import com.mary.starwars.domain.entity.Film
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IRepositoryRx {

    fun getFilm(id: String) : Single<Film>

    fun getAllFilms() : Single<List<Film>>

    fun deleteFilm(id: String) : Completable

    fun listenForNewFilm() : Flowable<Film>
}