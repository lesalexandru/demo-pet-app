package com.example.demopetapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demopetapp.domain.ViewState
import com.example.demopetapp.domain.entity.AnimalDetails
import com.example.demopetapp.domain.usecase.GetAnimalDetailsTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalDetailsViewModel @Inject constructor(
    private val getAnimalDetailsTask: GetAnimalDetailsTask
) : ViewModel() {

    private val _animalStateFlow: MutableStateFlow<ViewState<AnimalDetails>> = MutableStateFlow(ViewState.Loading)
    val animalStateFlow: StateFlow<ViewState<AnimalDetails>> = _animalStateFlow

    fun getAnimalDetails(id: Long) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _animalStateFlow.update {
                ViewState.Error(throwable.localizedMessage ?: "")
            }
        }

        _animalStateFlow.update { ViewState.Loading }
        viewModelScope.launch(exceptionHandler) {
            getAnimalDetailsTask(id).collect { animalDetails ->
                _animalStateFlow.update { ViewState.Success(animalDetails) }
            }
        }
    }
}
