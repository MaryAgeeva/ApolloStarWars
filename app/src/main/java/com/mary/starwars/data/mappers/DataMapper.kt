package com.mary.starwars.data.mappers

import com.mary.starwars.domain.entity.*
import com.mary.starwars.domain.utils.empty
import com.mary.starwars.fragment.CharacterFragment
import com.mary.starwars.fragment.FilmFragment
import com.mary.starwars.fragment.PlanetFragment

internal fun FilmFragment.toFilm() : Film {
    return Film(
        id = id,
        title = title?: String.empty(),
        director = director?: String.empty(),
        episode = episodeID?: 0,
        characters =  characterConnection?.characters?.mapNotNull {
            it?.fragments?.characterFragment?.toCharacter()
        }?: listOf(),
        planets = planetConnection?.planets?.mapNotNull {
            it?.fragments?.planetFragment?.toPlanet()
        }?: listOf()
    )
}

private fun CharacterFragment.toCharacter() : Character {
    return Character(
        id = id,
        name = name?: String.empty(),
        gender = gender?.toGender() ?: Gender.UNKNOWN,
        hairColor = hairColor?.toColor()?: Color.OTHER,
        species = species?.name?: String.empty()
    )
}

private fun PlanetFragment.toPlanet() : Planet {
    return Planet(
        id = id,
        title = name?: String.empty(),
        diameter = diameter ?: 0,
        population = population ?: 0.0,
        climate = climates?.filterNotNull()?: listOf()
    )
}

private fun String?.toGender() : Gender {
    return when(this) {
        "male" -> Gender.MALE
        "female" -> Gender.FEMALE
        else -> Gender.UNKNOWN
    }
}

private fun String?.toColor() : Color {
    return when(this) {
        "white" -> Color.WHITE
        "black" -> Color.BLACK
        "brown" -> Color.BROWN
        else -> Color.OTHER
    }
}