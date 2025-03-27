package com.plcoding.starwars.data.remote

// to map response
data class PlanetsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PlanetDto>
)