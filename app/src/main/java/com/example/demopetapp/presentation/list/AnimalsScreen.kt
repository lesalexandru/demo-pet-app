package com.example.demopetapp.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.demopetapp.R
import com.example.demopetapp.domain.ViewState
import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.presentation.ui.NavigationActions
import com.example.demopetapp.presentation.ui.components.AnimalCard
import com.example.demopetapp.presentation.ui.components.ErrorScreen
import com.example.demopetapp.presentation.ui.components.LoadingScreen

@Composable
fun AnimalsScreen(actions: NavigationActions) {

    val viewModel: AnimalListViewModel = hiltViewModel()
    val animalsScreenState by viewModel.animalsStateFlow.collectAsState()
    val lazyPagingAnimals = viewModel.getPagingAnimals().collectAsLazyPagingItems()

    AnimalsScreen(
        animalsScreenState = animalsScreenState,
        pagingAnimals = lazyPagingAnimals,
        goToDetailsScreen = { animalId: Long ->
            actions.goToDetailsScreen(animalId)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnimalsScreen(
    animalsScreenState: ViewState<List<Animal>>,
    pagingAnimals: LazyPagingItems<Animal>,
    goToDetailsScreen: (id: Long) -> Unit
) {

    val lazyListState: LazyListState = rememberLazyListState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                if (pagingAnimals.loadState.refresh == LoadState.Loading) {
                    item {
                        LoadingScreen()
                        Text(
                            text = stringResource(id = R.string.loading_items),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                } else if (pagingAnimals.loadState.refresh is LoadState.Error) {
                    item {
                        val errorState = pagingAnimals.loadState.refresh as LoadState.Error
                        ErrorScreen(
                            message = errorState.error.localizedMessage
                                ?: stringResource(id = R.string.unknown_error)
                        )
                    }
                }

                items(count = pagingAnimals.itemCount) { index ->
                    pagingAnimals[index]?.let { animal ->
                        AnimalCard(
                            animal = animal,
                            goToDetailsScreen = { goToDetailsScreen(animal.id) },
                        )
                    }
                }

                if (pagingAnimals.loadState.append == LoadState.Loading) {
                    item {
                        LoadingScreen()
                    }
                }
            }
        }
    }
}
