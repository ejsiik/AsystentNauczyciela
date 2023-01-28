package com.example.asystentnauczyciela.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val dayId: Int,
    val dayName: String,
    val timeFrom: String,
    val timeTo: String,
)