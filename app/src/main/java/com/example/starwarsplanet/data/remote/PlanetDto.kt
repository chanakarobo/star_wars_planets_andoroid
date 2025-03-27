package com.plcoding.starwars.data.remote

import com.google.gson.annotations.SerializedName

// use this class as remote model for APi purposes
data class PlanetDto(
    val name: String,
    val climate: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    val gravity: String,
    val url: String
)
