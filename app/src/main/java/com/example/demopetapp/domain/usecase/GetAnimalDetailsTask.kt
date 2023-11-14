package com.example.demopetapp.domain.usecase

import com.example.demopetapp.domain.entity.AnimalDetails
import com.example.demopetapp.domain.repository.AnimalDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimalDetailsTask @Inject constructor(
    private val animalDetailsRepository: AnimalDetailsRepository
) {
    suspend operator fun invoke(id: Long): Flow<AnimalDetails> =
        animalDetailsRepository.getAnimalDetails(id)
}
