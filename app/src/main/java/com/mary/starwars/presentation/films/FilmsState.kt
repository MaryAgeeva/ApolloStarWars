package com.mary.starwars.presentation.films

sealed class FilmsState {

    data class Success(val films: List<FilmViewObject>) : FilmsState()

    object Loading : FilmsState()

    object Error : FilmsState()
}