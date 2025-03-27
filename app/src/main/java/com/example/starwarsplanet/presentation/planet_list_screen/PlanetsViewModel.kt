package com.example.starwarsplanet.presentation.planet_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.plcoding.starwars.data.local.PlanetEntity
import com.plcoding.starwars.data.mappers.toPlanet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    pager: Pager<Int, PlanetEntity>
): ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPlanet() }
        }
        .cachedIn(viewModelScope)
}