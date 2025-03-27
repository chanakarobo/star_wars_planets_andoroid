package com.example.starwarsplanet

import com.example.starwarsplanet.data.repositories.PlanetRepository
import com.plcoding.starwars.data.local.PlanetDao
import com.plcoding.starwars.data.local.PlanetDatabase
import com.plcoding.starwars.data.local.PlanetEntity
import com.plcoding.starwars.data.mappers.toPlanet
import com.plcoding.starwars.data.mappers.toPlanetEntity
import com.plcoding.starwars.data.remote.PlanetApi
import com.plcoding.starwars.data.remote.PlanetDto
import com.plcoding.starwars.data.remote.PlanetsResponse
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.assertj.core.api.Assertions.assertThat


class PlanetRemoteMediatorTest {
    @Mock
    lateinit var planetDao: PlanetDao
    @Mock
    lateinit var apiService: PlanetApi
    @Mock
    lateinit var planetDb: PlanetDatabase
    private lateinit var repository: PlanetRepository

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        // Mock database to return the fake DAO
        `when`(planetDb.planetDao()).thenReturn(planetDao)
        repository = PlanetRepository(planeDb =  planetDb, planetApi = apiService)
    }

// test that the Mediator correctly syncs data to the database and fetches from the network.
    @Test
    fun `data correctly syncing up`() = runTest {
        val planetDtoList = listOf(
            PlanetDto(
                "Tatooine", "arid", "2000", "1 stand",
                url = ""
            ),
            PlanetDto(
                "Alderaan", "temperate", "grasslands, mountains", "2 stand",
                url = ""
            )
        )
        val planetResponse = PlanetsResponse(
            count = 2,
            next = "",
            previous = "",
            results = planetDtoList
        )

        `when`(apiService.getPlanets(page = 1)).thenReturn(planetResponse)
        repository.getPlanets()

        verify(apiService, times(1)).getPlanets(1)
        verify(planetDb.planetDao().insertAll(planetDtoList.map { it.toPlanetEntity() }))
    }

// should return correct planet according to the Id
    @Test
    fun `getPlanetById should return correct Planet`() = runTest {
        val planetId = 1
        val fakeEntity = PlanetEntity(
            id = planetId, name = "Tatooine",
            climate = "Arid",
            orbitalPeriod = "232",
            gravity = "1 standard",
            nextPage = 2,
            imageUrl = ""
        )
        val expectedPlanet = fakeEntity.toPlanet()

        `when`(planetDb.planetDao().getPlanetById(planetId)).thenReturn(fakeEntity)

        val result = repository.getPlanetById(planetId)
        assertThat(result).isEqualTo(expectedPlanet)
        verify(planetDb.planetDao(), times(1)).getPlanetById(planetId)
    }



}