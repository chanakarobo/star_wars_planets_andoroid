package com.plcoding.starwars.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.plcoding.starwars.data.local.PlanetDao
import com.plcoding.starwars.data.local.PlanetDatabase
import com.plcoding.starwars.data.local.PlanetEntity
import com.plcoding.starwars.data.remote.PlanetApi
import com.plcoding.starwars.data.remote.PlanetRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    provide Database module
    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): PlanetDatabase {
        return Room.databaseBuilder(
            context,
            PlanetDatabase::class.java,
            "planet.db"
        ).build()
    }

//   provide Api module
    @Provides
    @Singleton
    fun providePlanetApi(): PlanetApi {
        return Retrofit.Builder()
            .baseUrl(PlanetApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

//    provide data access object
    @Provides
    fun providePlanetDao(database: PlanetDatabase): PlanetDao {
        return database.planetDao()
    }

//    provide pager class to enable pagination
    @Provides
    @Singleton
    fun providePlanetPager(planedDb: PlanetDatabase, planetApi: PlanetApi): Pager<Int, PlanetEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PlanetRemoteMediator(
                planeDb = planedDb,
                planetApi = planetApi
            ),
            pagingSourceFactory = {
                planedDb.planetDao().pagingSource()
            }
        )
    }
}