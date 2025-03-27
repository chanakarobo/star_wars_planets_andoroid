package com.plcoding.starwars.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
// planet table in the local db
@Entity
data class PlanetEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val climate: String,
    val orbitalPeriod: String?,
    val gravity: String,
    val nextPage: Int?,
    val imageUrl: String = "https://picsum.photos/600/400?random=$id"
)