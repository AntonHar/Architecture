package com.example.cleanarchitechture.domain

import kotlinx.coroutines.flow.Flow

interface OperationsUseCase {

    suspend fun getOperations() : Flow<List<Operation>>

    suspend fun deleteOperation(operation: Operation)
}