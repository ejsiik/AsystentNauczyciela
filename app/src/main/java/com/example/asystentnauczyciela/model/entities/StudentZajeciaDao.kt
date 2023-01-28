package com.example.asystentnauczyciela.model.entities

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface StudentZajeciaDao {
    @Insert
    fun addStudentSubjectCross(studentZajecia: StudentZajecia)
}