package com.mary.starwars.domain.entity

data class Character(
    val id: String,
    val name: String,
    val gender: Gender,
    val hairColor: Color,
    val species: String
)