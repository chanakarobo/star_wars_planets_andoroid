package com.plcoding.starwars.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApi {

    @GET("planets/")
    suspend fun getPlanets(
        @Query("page") page: Int,
    ): PlanetsResponse

    companion object {
        const val BASE_URL = "https://swapi.dev/api/"
    }
}