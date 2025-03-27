package com.example.starwarsplanet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwarsplanet.ui.theme.StarWarsPlanetTheme
import com.example.starwarsplanet.presentation.planet_details_screen.PlanetDetailScreen
import com.example.starwarsplanet.presentation.planet_list_screen.PlanetsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsPlanetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StarWars()
                }
            }
        }
    }
}

@Composable
fun StarWars() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "planets") {
//        we can save those routes in static way but I didn't do it for small application
        composable("planets") {
            PlanetsScreen(navController = navController)
        }
//        pass the planet id to detail screen when click on the item
        composable("planet_detail/{planetId}") { backStackEntry ->
            val planetId = backStackEntry.arguments?.getString("planetId")?.toIntOrNull()
            if (planetId != null) {
                PlanetDetailScreen(planetId)
            }
        }
    }
}
