package com.example.cleanarchitechture.domain.usecase.person

import com.example.cleanarchitechture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

class PersonsUseCaseImpl(private val personsRepository: PersonsRepository): PersonsUseCase {
    override fun getPersons(): Flow<List<Person>> =
        personsRepository.getPersons()
}