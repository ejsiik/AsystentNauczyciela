package com.example.asystentnauczyciela.model.repositories

import com.example.asystentnauczyciela.model.entities.StudentZajeciaDao
import com.example.asystentnauczyciela.model.entities.StudentZajecia

class StudentZajeciaRepository(private val studentZajeciaDao: StudentZajeciaDao) {
    fun addStuStudentSubjectCross(studentZajecia: StudentZajecia) {
        studentZajeciaDao.addStudentSubjectCross(studentZajecia)
    }
}