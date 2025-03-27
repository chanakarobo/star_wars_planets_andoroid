package com.plcoding.starwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
// database configuration
@Database(
    entities = [PlanetEntity::class],
    version = 1
)
abstract class PlanetDatabase: RoomDatabase() {
    abstract fun planetDao(): PlanetDao
}