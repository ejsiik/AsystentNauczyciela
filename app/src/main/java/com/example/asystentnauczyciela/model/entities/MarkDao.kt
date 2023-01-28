package com.example.asystentnauczyciela.model.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MarkDao {
    @Insert
    fun addMark(mark: Mark)

    @Query(
        "SELECT * FROM marks " +
                "WHERE StudentId = :studentId " +
                "AND SubjectId = :subjectId"
    )
    fun getSubjectStudentMarks(subjectId: String, studentId: String): LiveData<List<Mark>>
}