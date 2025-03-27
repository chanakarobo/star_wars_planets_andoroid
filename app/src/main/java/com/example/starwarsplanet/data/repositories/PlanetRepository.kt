package com.example.starwarsplanet.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.plcoding.starwars.data.local.PlanetDatabase
import com.plcoding.starwars.data.local.PlanetEntity
import com.plcoding.starwars.data.mappers.toPlanet
import com.plcoding.starwars.data.remote.PlanetRemoteMediator
import com.plcoding.starwars.data.remote.PlanetApi
import com.plcoding.starwars.domain.Planet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlanetRepository  @Inject constructor(
    private val planeDb: PlanetDatabase,
    private val planetApi: PlanetApi
){

// Pager Api I used alpha version that's I had to annotate as ExperimentalPagingApi
    @OptIn(ExperimentalPagingApi::class)
    fun getPlanets(): Flow<PagingData<PlanetEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PlanetRemoteMediator(
                planeDb = planeDb,
                planetApi = planetApi
            ),
            pagingSourceFactory = { planeDb.planetDao().pagingSource() }
        ).flow
    }

//    retrieve planets by it's ID to show on Details Screen
    suspend fun getPlanetById(id: Int): Planet? {
        return planeDb.planetDao().getPlanetById(id)?.toPlanet()
    }


}