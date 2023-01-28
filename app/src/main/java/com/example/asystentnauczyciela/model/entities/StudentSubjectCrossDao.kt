package com.example.asystentnauczyciela.model.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentSubjectCrossDao {
    @Insert
    fun addStudentSubjectCross(studentSubjectCrossRef: StudentSubjectCrossRef)

    @Query("DELETE FROM studentSubjectCross WHERE studentId = :studentId AND subjectId = :subjectId")
    fun deleteById(studentId: String, subjectId: String)
}