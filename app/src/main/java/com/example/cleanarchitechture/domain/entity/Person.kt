package com.example.cleanarchitechture.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    val name: String,
    var rating: Float,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
