package com.mary.starwars.domain.example_repository

import com.mary.starwars.domain.entity.Film
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IRepositoryRx {

    fun getFilm(id: String) : Single<Film>

    fun getAllFilms() : Single<List<Film>>

    fun deleteFilm(id: String) : Completable

    fun listenForNewFilm() : Flowable<Film>
}