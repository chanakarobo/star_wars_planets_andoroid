package com.plcoding.starwars.data.mappers

import com.plcoding.starwars.data.local.PlanetEntity
import com.plcoding.starwars.data.remote.PlanetDto
import com.plcoding.starwars.domain.Planet


fun PlanetDto.toPlanetEntity(page: Int? = null): PlanetEntity {
    val id = url.split("/").filter { it.isNotEmpty() }.last().toInt()
    return PlanetEntity(
        id = id,
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity,
        nextPage = page
    )
}

fun PlanetEntity.toPlanet(): Planet {
    return Planet(
        id = id,
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity,
        imageUrl = imageUrl
    )
}