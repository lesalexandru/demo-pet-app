package com.example.demopetapp.domain.repository

import com.example.demopetapp.domain.entity.AnimalDetails
import kotlinx.coroutines.flow.Flow

interface AnimalDetailsRepository {

    suspend fun getAnimalDetails(id: Long): Flow<AnimalDetails>
}
