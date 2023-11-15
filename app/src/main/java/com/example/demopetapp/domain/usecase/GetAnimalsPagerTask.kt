package com.example.demopetapp.domain.usecase

import androidx.paging.PagingData
import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.domain.repository.AnimalListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimalsPagerTask @Inject constructor(
    private val animalListRepository: AnimalListRepository
) {
    operator fun invoke(): Flow<PagingData<Animal>> =
        animalListRepository.getPagerAnimals()
}
