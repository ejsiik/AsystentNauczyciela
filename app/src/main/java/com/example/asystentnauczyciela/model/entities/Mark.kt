package com.example.asystentnauczyciela.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marks")
data class Mark(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentId: Int,
    val subjectId: Int,
    val value: Float,
    val evaluatedItem: String
)