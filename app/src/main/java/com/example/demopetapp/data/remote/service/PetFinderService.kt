package com.example.demopetapp.data.remote.service

import com.example.demopetapp.data.remote.dto.AnimalDto
import com.example.demopetapp.data.remote.dto.AnimalsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PetFinderService {

    @GET("animals")
    suspend fun getAnimals(
        @Query("page") page: Int,
        @Query("type") type: String? = null
    ): AnimalsDto

    @GET("animals/{id}")
    suspend fun getAnimal(
        @Path("id") id: Long
    ): AnimalDto
}
