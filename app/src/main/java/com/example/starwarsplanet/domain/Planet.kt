package com.plcoding.starwars.domain

data class Planet(
    val id: Int,
    val name: String,
    val climate: String,
    val orbitalPeriod: String?,
    val gravity: String,
    val imageUrl: String?
)
