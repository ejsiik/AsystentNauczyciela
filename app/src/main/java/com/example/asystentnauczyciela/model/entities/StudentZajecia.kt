package com.example.asystentnauczyciela.model.entities

import androidx.room.Entity

@Entity(tableName = "studentSubjectCross", primaryKeys = ["studentId", "subjectId"])
data class StudentZajecia(
    val studentId: Int,
    val subjectId: Int
)