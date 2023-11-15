package com.example.demopetapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.demopetapp.domain.ViewState
import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.domain.usecase.GetAnimalsPagerTask
import com.example.demopetapp.domain.usecase.GetAnimalsTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalListViewModel @Inject constructor(
    private val getAnimalsTask: GetAnimalsTask,
    private val getAnimalsPagerTask: GetAnimalsPagerTask
) : ViewModel() {

    private val _animalsStateFlow: MutableStateFlow<ViewState<List<Animal>>> = MutableStateFlow(ViewState.Loading)
    val animalsStateFlow: StateFlow<ViewState<List<Animal>>> = _animalsStateFlow

    fun getAnimals() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _animalsStateFlow.update {
                ViewState.Error(throwable.localizedMessage ?: "")
            }
        }

        _animalsStateFlow.update { ViewState.Loading }
        viewModelScope.launch(exceptionHandler) {
            getAnimalsTask(1).collect { result ->
                _animalsStateFlow.update {
                    ViewState.Success(result)
                }
            }
        }
    }

    fun getPagingAnimals(): Flow<PagingData<Animal>> = getAnimalsPagerTask().cachedIn(viewModelScope)
}
