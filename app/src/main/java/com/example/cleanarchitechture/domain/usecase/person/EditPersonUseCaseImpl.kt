package com.example.cleanarchitechture.domain.usecase.person

import com.example.cleanarchitechture.domain.entity.Person

class EditPersonUseCaseImpl(private val personsRepository: PersonsRepository) : EditPersonUseCase {
    override fun addPerson(person: Person) {
        personsRepository.addPerson(person)
    }
}