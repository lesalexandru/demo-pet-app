package com.example.demopetapp.data.repository

import com.example.demopetapp.data.mapper.toAnimalDetails
import com.example.demopetapp.data.remote.service.PetFinderService
import com.example.demopetapp.di.DispatcherModule.IoDispatcher
import com.example.demopetapp.domain.entity.AnimalDetails
import com.example.demopetapp.domain.repository.AnimalDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimalDetailsRepositoryImpl @Inject constructor(
    private val petFinderService: PetFinderService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AnimalDetailsRepository {
    override suspend fun getAnimalDetails(id: Long): Flow<AnimalDetails> =
        flow {
            emit(petFinderService.getAnimal(id).toAnimalDetails())
        }.flowOn(ioDispatcher)
}
