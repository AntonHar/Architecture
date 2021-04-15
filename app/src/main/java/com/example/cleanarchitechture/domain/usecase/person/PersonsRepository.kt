package com.example.cleanarchitechture.domain.usecase.person

import com.example.cleanarchitechture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {

    fun addPerson(person: Person)
    fun getPersons(): Flow<List<Person>>
}