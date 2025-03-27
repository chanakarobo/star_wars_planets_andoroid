package com.example.starwarsplanet.presentation.planet_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsplanet.data.repositories.PlanetRepository
import com.plcoding.starwars.domain.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val planetRepository: PlanetRepository
) : ViewModel() {

    private val _planet = MutableStateFlow<Planet?>(null)
    val planet: StateFlow<Planet?> = _planet.asStateFlow()

    fun loadPlanet(id: Int) {
        viewModelScope.launch {
            _planet.value = planetRepository.getPlanetById(id)
        }
    }

}