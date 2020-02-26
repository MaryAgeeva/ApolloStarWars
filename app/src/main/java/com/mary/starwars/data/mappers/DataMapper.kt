package com.mary.starwars.data.mappers

import com.mary.starwars.domain.entity.*
import com.mary.starwars.domain.utils.empty
import com.mary.starwars.fragment.CharacterFragment
import com.mary.starwars.fragment.FilmFragment
import com.mary.starwars.fragment.PlanetFragment
import com.mary.starwars.type.PERSON_GENDER
import com.mary.starwars.type.PERSON_HAIR_COLOR

internal fun FilmFragment.toFilm() : Film {
    return Film(
        id = id(),
        title = title(),
        director = director()?: String.empty(),
        episode = episodeId(),
        characters =  characters()?.map {
            it.fragments().characterFragment().toCharacter()
        }?: listOf(),
        planets = planets()?.map {
            it.fragments().planetFragment().toPlanet()
        }?: listOf()
    )
}

private fun CharacterFragment.toCharacter() : Character {
    return Character(
        id = id(),
        name = name(),
        gender = gender().toGender(),
        hairColor = if(!hairColor().isNullOrEmpty()) hairColor()?.first()?.toColor()?: Color.OTHER else Color.OTHER,
        species = if(!species().isNullOrEmpty()) species()?.first()?.name()?: String.empty() else String.empty()
    )
}

private fun PlanetFragment.toPlanet() : Planet {
    return Planet(
        id = id(),
        title = name(),
        diameter = diameter()?: 0,
        population = population()?: 0.0,
        climate = climate()?: listOf()
    )
}

private fun PERSON_GENDER?.toGender() : Gender {
    return when(this) {
        PERSON_GENDER.MALE -> Gender.MALE
        PERSON_GENDER.FEMALE -> Gender.FEMALE
        else -> Gender.UNKNOWN
    }
}

private fun PERSON_HAIR_COLOR?.toColor() : Color {
    return when(this) {
        PERSON_HAIR_COLOR.WHITE -> Color.WHITE
        PERSON_HAIR_COLOR.BLACK -> Color.BLACK
        PERSON_HAIR_COLOR.BROWN -> Color.BROWN
        else -> Color.OTHER
    }
}