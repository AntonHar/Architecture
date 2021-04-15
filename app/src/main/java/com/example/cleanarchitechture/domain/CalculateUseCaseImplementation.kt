package com.example.cleanarchitechture.domain

class CalculateUseCaseImplementation(
    private val calculateRepository: CalculateRepository,
    private val operationsRepository: OperationsRepository
) : CalculateUseCase {

    override suspend fun calculate(first: Int, second: Int): Int {
        val result = calculateRepository.calculate(Operation(first, second))
        val operation = Operation(first, second, result)
        operationsRepository.addOperation(operation)
        return result
    }
}
