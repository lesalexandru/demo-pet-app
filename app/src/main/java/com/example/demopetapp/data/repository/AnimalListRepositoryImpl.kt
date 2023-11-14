package com.example.demopetapp.data.repository

import com.example.demopetapp.data.mapper.toAnimal
import com.example.demopetapp.data.remote.service.PetFinderService
import com.example.demopetapp.di.DispatcherModule.IoDispatcher
import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.domain.repository.AnimalListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimalListRepositoryImpl @Inject constructor(
    private val petFinderService: PetFinderService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AnimalListRepository {
    override suspend fun getAnimals(page: Int): Flow<List<Animal>> =
        flow {
            emit(
                petFinderService.getAnimals(page).animals.map { animalDto ->
                    animalDto.toAnimal()
                }
            )
        }.flowOn(ioDispatcher)

}
