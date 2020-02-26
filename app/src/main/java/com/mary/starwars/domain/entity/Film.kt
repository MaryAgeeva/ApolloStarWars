package com.mary.starwars.domain.entity

data class Film(
    val id: String,
    val title: String,
    val director: String,
    val episode: Int,
    val characters: List<Character>,
    val planets: List<Planet>
)