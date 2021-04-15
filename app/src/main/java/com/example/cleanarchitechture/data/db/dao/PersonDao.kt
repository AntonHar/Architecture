package com.example.cleanarchitechture.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cleanarchitechture.domain.entity.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): Flow<List<Person>>


//    @Query("SELECT * FROM Person WHERE id IN (:userIds)")
//    fun findAllByIds(userIds: IntArray): List<Person>
//
//    @Query("SELECT * FROM Person WHERE name LIKE :first")
//    fun findByName(first: String, last: String): Person
//
//    @Insert
//    fun insertAll(vararg persons: Person)

    @Insert
    fun insert(person: Person)

//    @Delete
//    fun delete(person: Person)
}