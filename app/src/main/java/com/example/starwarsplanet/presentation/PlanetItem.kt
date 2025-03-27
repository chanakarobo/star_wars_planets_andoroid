package com.plcoding.starwars.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.starwarsplanet.ui.theme.StarWarsPlanetTheme
import com.plcoding.starwars.domain.Planet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlanetItem(
    planet: Planet,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = planet.imageUrl,
                contentDescription = planet.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = planet.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Climate: ${planet.climate}",
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun PlanetItemPreview() {
    StarWarsPlanetTheme {
        PlanetItem(
            planet = Planet(
                id = 1,
                name = "Socorro",
                climate = "arid",
                imageUrl = null,
                orbitalPeriod = "326",
                gravity = "1 standard",
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )
    }
}