package com.example.demopetapp.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.demopetapp.R
import com.example.demopetapp.domain.ViewState
import com.example.demopetapp.domain.entity.AnimalDetails
import com.example.demopetapp.presentation.ui.components.ErrorScreen
import com.example.demopetapp.presentation.ui.components.LoadingScreen
import com.example.demopetapp.presentation.ui.components.SingleLineText
import com.example.demopetapp.presentation.ui.theme.DemoPetAppTheme

@Composable
fun AnimalDetailsScreen(animalId: Long?) {

    val viewModel: AnimalDetailsViewModel = hiltViewModel()
    val viewState by viewModel.animalIdStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        if (animalId != null && viewState is ViewState.Loading) {
            viewModel.getAnimalDetails(animalId)
        }
    }
    DetailsScreen(animalDetailsViewState = viewState)
}

@Composable
private fun DetailsScreen(animalDetailsViewState: ViewState<AnimalDetails>) {
    Box(Modifier.fillMaxSize()) {
        when (animalDetailsViewState) {
            ViewState.Loading -> LoadingScreen()

            is ViewState.Success -> DetailsScreen(animalDetailsViewState.data)

            else -> ErrorScreen(
                message = if (animalDetailsViewState is ViewState.Error)
                    animalDetailsViewState.message
                else
                    stringResource(id = R.string.unknown_error)
            )
        }
    }
}

@Composable
private fun DetailsScreen(animalDetails: AnimalDetails) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Box(
                modifier = Modifier
                    .weight(0.55f)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SingleLineText(
                        text = animalDetails.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        val detailsList = listOf(
                            stringResource(id = R.string.breed_label) to animalDetails.breed,
                            stringResource(id = R.string.size_label) to animalDetails.size,
                            stringResource(id = R.string.gender_label) to animalDetails.gender,
                            stringResource(id = R.string.status_label) to animalDetails.status,
                            stringResource(id = R.string.distance_label)
                                    to (animalDetails.distance?.toString()
                                ?: stringResource(id = R.string.n_a))
                        )
                        Column {
                            detailsList.map { (description, _) ->
                                Text(text = description, fontWeight = FontWeight.SemiBold)
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            detailsList.map { (_, text) ->
                                Text(text)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DemoPetAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            DetailsScreen(
                AnimalDetails(
                    "Animal name",
                    "Breed",
                    "size",
                    "gender",
                    "status",
                    4.0
                )
            )
        }
    }
}
