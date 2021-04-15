package com.example.cleanarchitechture.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitechture.Dependencies
import com.example.cleanarchitechture.domain.CalculateUseCase
import com.example.cleanarchitechture.domain.Operation
import com.example.cleanarchitechture.domain.OperationsUseCase
import com.example.cleanarchitechture.domain.entity.Person
import com.example.cleanarchitechture.domain.usecase.person.EditPersonUseCase
import com.example.cleanarchitechture.domain.usecase.person.PersonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val calculateUseCase: CalculateUseCase by lazy { Dependencies.getCalculateUseCase() }
    private val operationsUseCase: OperationsUseCase by lazy { Dependencies.getOperationsUseCase() }
    private val personUseCase: PersonsUseCase by lazy { Dependencies.getPersonsUseCase() }
    private val editPersonUseCase: EditPersonUseCase by lazy { Dependencies.getEditPersonUseCase() }

    var name: String = ""
    var rating: String = ""

    private var persons = MutableLiveData<List<Person>>(listOf())

    fun getPersons(): LiveData<List<Person>> {
        return persons
    }

    private var _calculationState = MutableLiveData<CalculationState>(CalculationState.Free)

    val calculationState: LiveData<CalculationState> = _calculationState

    fun calculate(): Int {
        _calculationState.value = CalculationState.Loading
        var result = 0
        viewModelScope.launch {
            val firstValue = try {
                name.toInt()
            } catch (ex: Exception) {
                0
            }
            val secondValue = try {
                rating.toInt()
            } catch (ex: Exception) {
                0
            }
            result = calculateUseCase.calculate(firstValue, secondValue)
            val person = Person("Vasya", 21f)
            _calculationState.value = CalculationState.Result
            freeStateWithDelay()
        }

        return result
    }

    fun addPerson() {
        val rating = try {
            this.rating.toFloat()
        } catch (exception: Exception) {
            0F
        }
        val person = Person(name, rating)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                editPersonUseCase.addPerson(person)
            }
        }
    }

    private suspend fun freeStateWithDelay() {
        delay(1000)
        _calculationState.value = CalculationState.Free
    }

    fun onOperationSelected(person: Person) {
//        viewModelScope.launch {
//            operationsUseCase.deleteOperation(operation)
//        }
    }

    init {
        viewModelScope.launch {
            personUseCase.getPersons().collect {
                persons.value = it
            }
        }
    }
}
