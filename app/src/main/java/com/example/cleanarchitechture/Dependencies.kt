package com.example.cleanarchitechture

import com.example.cleanarchitechture.data.OperationsLocalSource
import com.example.cleanarchitechture.data.SumCalculator
import com.example.cleanarchitechture.data.db.LocalDatabaseSource
import com.example.cleanarchitechture.domain.*
import com.example.cleanarchitechture.domain.usecase.person.EditPersonUseCase
import com.example.cleanarchitechture.domain.usecase.person.EditPersonUseCaseImpl
import com.example.cleanarchitechture.domain.usecase.person.PersonsUseCase
import com.example.cleanarchitechture.domain.usecase.person.PersonsUseCaseImpl

object Dependencies {

    private val operationsRepository: OperationsRepository by lazy { OperationsLocalSource() }
    private val localDatabaseSource: LocalDatabaseSource by lazy { LocalDatabaseSource(App.instance) }

    private fun getCalculateRepository(): CalculateRepository {
        return SumCalculator()
    }

    private fun getOperaationsRepository(): OperationsRepository {
        return operationsRepository
    }

    fun getCalculateUseCase(): CalculateUseCase {
        return CalculateUseCaseImplementation(getCalculateRepository(), getOperaationsRepository())
    }

    fun getOperationsUseCase(): OperationsUseCase {
        return OperationsUseCaseImpl(getOperaationsRepository())
    }

    fun getPersonsUseCase(): PersonsUseCase =
        PersonsUseCaseImpl(localDatabaseSource)

    fun getEditPersonUseCase(): EditPersonUseCase =
        EditPersonUseCaseImpl(localDatabaseSource)
}