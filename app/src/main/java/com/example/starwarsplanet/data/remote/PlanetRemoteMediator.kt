package com.plcoding.starwars.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.plcoding.starwars.data.local.PlanetDatabase
import com.plcoding.starwars.data.local.PlanetEntity
import com.plcoding.starwars.data.mappers.toPlanetEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val planeDb: PlanetDatabase,
    private val planetApi: PlanetApi
): RemoteMediator<Int, PlanetEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlanetEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        lastItem?.nextPage ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                }
            }
// get the response from API
            val response = planetApi.getPlanets(
                page = loadKey,
            )
//            map it to Entity class
            val planets = response.results.map { it.toPlanetEntity(loadKey + 1) }
// this should be synchronized for that should use withTransaction
            planeDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    planeDb.planetDao().clearAll()
                }
                planeDb.planetDao().insertAll(planets)
            }
// stop data retrieving if next is null
            MediatorResult.Success(
                endOfPaginationReached = response.next == null
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}