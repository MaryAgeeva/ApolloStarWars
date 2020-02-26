package com.mary.starwars.domain.entity

data class Planet(
    val id: String,
    val title: String,
    val diameter: Int,
    val population: Double,
    val climate: List<String>
)