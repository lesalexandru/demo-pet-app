package com.example.demopetapp.domain.usecase

import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.domain.repository.AnimalListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimalsTask @Inject constructor(
    private val animalListRepository: AnimalListRepository
) {

    suspend operator fun invoke(page: Int): Flow<List<Animal>> =
        animalListRepository.getAnimals(page)
}
