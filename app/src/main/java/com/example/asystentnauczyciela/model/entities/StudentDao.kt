package com.example.asystentnauczyciela.model.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: Student)

    @Query("SELECT * FROM students")
    fun getAllStudents(): LiveData<List<Student>>

    @Query(
        "SELECT * FROM students " +
                "WHERE id not in (" +
                "SELECT StudentId FROM studentSubjectCross " +
                "WHERE SubjectId = :subjectId)"
    )
    fun getUnEnrolledStudents(subjectId: String): LiveData<List<Student>>

    @Query(
        "SELECT * " +
                "FROM students s JOIN studentSubjectCross ssc " +
                "ON s.id = ssc.studentId " +
                "WHERE ssc.subjectId = :subjectId"
    )
    fun getEnrolledStudents(subjectId: String): LiveData<List<Student>>

    @Query("DELETE FROM students WHERE id = :studentId")
    fun deleteById(studentId: String)
}