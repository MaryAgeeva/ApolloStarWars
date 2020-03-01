package com.mary.starwars.presentation.mappers

import com.mary.starwars.domain.entity.Film
import com.mary.starwars.presentation.films.FilmViewObject

internal fun List<Film>.toFilmViews() : List<FilmViewObject> {
    return map { film ->
        film.toFilmView()
    }
}

private fun Film.toFilmView() : FilmViewObject {
    return FilmViewObject(
        id = id,
        episodeId = episode,
        title = title
    )
}