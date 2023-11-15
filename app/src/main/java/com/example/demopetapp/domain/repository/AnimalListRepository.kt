package com.example.demopetapp.domain.repository

import androidx.paging.PagingData
import com.example.demopetapp.domain.entity.Animal
import kotlinx.coroutines.flow.Flow

interface AnimalListRepository {

    suspend fun getAnimals(page: Int): Flow<List<Animal>>

    fun getPagerAnimals(): Flow<PagingData<Animal>>
}
