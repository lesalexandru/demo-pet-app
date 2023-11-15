package com.example.demopetapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.presentation.ui.theme.DemoPetAppTheme
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun AnimalCard(
    animal: Animal,
    goToDetailsScreen: () -> Unit,
) {
    AnimalCard(
        photo = {
            CoilImage(
                imageModel = animal.photo ?: "",
                contentScale = ContentScale.Crop,
                loading = { LoadingImage() },
                failure = { NoImage() },
                modifier = Modifier
                    .size(width = 150.dp, height = 200.dp)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { goToDetailsScreen() },
            )
        },
        animalDetails = { clipShape ->
            Surface(
                shape = clipShape,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .clip(clipShape)
                    .clickable { goToDetailsScreen() }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SingleLineText(
                        text = animal.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    SingleLineText(text = animal.gender)
                }
            }
        }
    )
}

@Composable
private fun AnimalCard(
    photo: @Composable RowScope.() -> Unit,
    animalDetails: @Composable RowScope.(clipShape: Shape) -> Unit,
) {
    val clipShape = MaterialTheme.shapes.small.copy(
        topStart = CornerSize(0.dp),
        bottomStart = CornerSize(0.dp),
    )
    Row {
        photo()
        animalDetails(clipShape)
    }
}

@Preview
@Composable
fun AnimalCardPreview() {
    DemoPetAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            AnimalCard(animal = Animal(1, "", "Name example", "Gender"), goToDetailsScreen = {})
        }
    }
}