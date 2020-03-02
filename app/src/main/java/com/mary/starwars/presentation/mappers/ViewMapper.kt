package com.mary.starwars.presentation.mappers

import androidx.annotation.ColorRes
import com.mary.starwars.domain.entity.Color
import com.mary.starwars.domain.entity.Film
import com.mary.starwars.presentation.detail.CharacterViewObject
import com.mary.starwars.presentation.detail.FilmDetailViewObject
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

internal fun Film.toDetailFilmView() : FilmDetailViewObject {
    return FilmDetailViewObject(
        title = title,
        episodeId = episode,
        director = director,
        characters = characters.map {
            CharacterViewObject(
                name = it.name,
                species = it.species,
                hairColor = it.hairColor.toColor()
            )
        }
    )
}

@ColorRes private fun Color.toColor() : Int {
    return when(this) {
        Color.WHITE -> android.R.color.white
        Color.BLACK -> android.R.color.black
        Color.BROWN -> android.R.color.holo_orange_dark
        Color.BLUE -> android.R.color.holo_blue_light
        Color.RED -> android.R.color.holo_red_light
        Color.OTHER -> android.R.color.darker_gray
    }
}