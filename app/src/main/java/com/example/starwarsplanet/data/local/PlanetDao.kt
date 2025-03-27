package com.plcoding.starwars.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planetentity")
    fun pagingSource(): PagingSource<Int, PlanetEntity>

    @Query("SELECT * FROM planetentity WHERE id = :planetId")
    suspend fun getPlanetById(planetId: Int): PlanetEntity?

    @Query("DELETE FROM planetentity")
    suspend fun clearAll()
}