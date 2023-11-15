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

    private val _animalIdStateFlow: MutableStateFlow<ViewState<AnimalDetails>> = MutableStateFlow(ViewState.Loading)
    val animalIdStateFlow: StateFlow<ViewState<AnimalDetails>> = _animalIdStateFlow

    fun getAnimalDetails(id: Long) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _animalIdStateFlow.update {
                ViewState.Error(throwable.localizedMessage ?: "")
            }
        }

        _animalIdStateFlow.update { ViewState.Loading }
        viewModelScope.launch(exceptionHandler) {
            getAnimalDetailsTask(id).collect { animalDetails ->
                _animalIdStateFlow.update { ViewState.Success(animalDetails) }
            }
        }
    }
}
