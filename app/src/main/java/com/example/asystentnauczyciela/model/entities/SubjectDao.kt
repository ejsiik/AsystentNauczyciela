package com.example.asystentnauczyciela.model.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubjectDao {

    @Insert
    fun addSubject(subject: Subject)

    @Query("SELECT * FROM subjects Order by dayId, timeFrom")
    fun getAllSubjects(): LiveData<List<Subject>>
}