package com.example.cleanarchitechture.data.db

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitechture.domain.entity.Person
import com.example.cleanarchitechture.domain.usecase.person.PersonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalDatabaseSource(context: Context): PersonsRepository {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    )
        .build()

    private val personDao = db.personDao()

    override fun addPerson(person: Person) {
        personDao.insert(person)
    }

    override fun getPersons(): Flow<List<Person>> {
        return personDao.getAll()
    }
}