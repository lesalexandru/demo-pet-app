package com.example.demopetapp.data.mapper

import com.example.demopetapp.data.remote.dto.AnimalDto
import com.example.demopetapp.domain.entity.Animal
import com.example.demopetapp.domain.entity.AnimalDetails

fun AnimalDto.Animal.toAnimal() = Animal(
    id = id,
    photo = photos.firstOrNull()?.medium,
    name = name,
    gender = gender
)

fun AnimalDto.toAnimalDetails() = AnimalDetails(
    name = animal.name,
    breed = animal.breeds.primary,
    size = animal.size,
    gender = animal.gender,
    status = animal.status,
    distance = animal.distance
)
