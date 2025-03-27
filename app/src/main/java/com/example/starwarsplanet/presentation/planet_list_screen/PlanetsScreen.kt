package com.example.starwarsplanet.presentation.planet_list_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.plcoding.starwars.presentation.PlanetItem

@Composable
fun PlanetsScreen(
    navController: NavController,
    viewModel: PlanetsViewModel = hiltViewModel()
) {
    val planets = viewModel.beerPagingFlow.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = planets.loadState) {
        if(planets.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (planets.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(planets.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(planets) { planet ->
                    if(planet != null) {
                        PlanetItem(
                            planet = planet,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { navController.navigate("planet_detail/${planet.id}") }
                        )
                    }
                }
                item {
                    if(planets.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}