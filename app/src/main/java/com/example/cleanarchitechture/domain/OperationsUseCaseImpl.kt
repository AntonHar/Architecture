package com.example.cleanarchitechture.domain

import kotlinx.coroutines.flow.Flow

class OperationsUseCaseImpl(
    private val operationsRepository: OperationsRepository
) : OperationsUseCase {
    override suspend fun getOperations(): Flow<List<Operation>> {
        return operationsRepository.getOperations()
    }

    override suspend fun deleteOperation(operation: Operation) {
        operationsRepository.removeOperation(operation)
    }
}
