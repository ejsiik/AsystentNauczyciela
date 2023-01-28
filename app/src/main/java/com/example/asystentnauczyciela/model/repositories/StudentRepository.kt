package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.model.entities.StudentDao

class StudentRepository(private val studentDao: StudentDao) {
    val getAllStudents: LiveData<List<Student>> = studentDao.getAllStudents()
    fun addStudent(student: Student) {
        studentDao.addStudent(student)
    }

    fun getUnEnrolledStudentsList(studentId: Number): LiveData<List<Student>> {
        return studentDao.getUnEnrolledStudents(studentId.toString())
    }

    fun getEnrolledStudentsList(studentId: Number): LiveData<List<Student>> {
        return studentDao.getEnrolledStudents(studentId.toString())
    }

    fun deleteStudentById(studentId: Number) {
        return studentDao.deleteById(studentId.toString())
    }
}