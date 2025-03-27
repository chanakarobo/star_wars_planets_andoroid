package com.example.starwarsplanet.presentation.planet_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun PlanetDetailScreen(
    planetId: Int,
    viewModel: PlanetDetailViewModel = hiltViewModel()
) {
    val planet by viewModel.planet.collectAsState()

    LaunchedEffect(planetId) {
        viewModel.loadPlanet(planetId)
    }

    planet?.let { p ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            AsyncImage(
                model = p.imageUrl,
                contentDescription = "Planet ${p.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = p.name,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            PlanetDetailItem("Climate", p.name)
            PlanetDetailItem("Orbital Period", p.orbitalPeriod)
            PlanetDetailItem("Gravity", p.gravity)
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun PlanetDetailItem(label: String, value: String?) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            "${label}:",
        )
        Spacer(modifier = Modifier.width(10.dp))
        if (value != null) {
            Text(
                text = value,
            )
        }
    }
}