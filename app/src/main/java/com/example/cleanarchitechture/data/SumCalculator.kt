package com.example.cleanarchitechture.data

import com.example.cleanarchitechture.domain.CalculateRepository
import com.example.cleanarchitechture.domain.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*

class SumCalculator : CalculateRepository {

    override suspend fun calculate(operation: Operation): Int {
        var sum = 0
        val startTime = System.currentTimeMillis()
        println(System.currentTimeMillis())
        withContext(Dispatchers.IO) {
            // Emulating heavy calculation process
            for (i in 0..Int.MAX_VALUE / 10) {
                if (sum % 2 == 0) sum += i
                else sum -= i
            }
        }

        println("Calculation took ${System.currentTimeMillis() - startTime}")
        return operation.first + operation.second
    }
}
