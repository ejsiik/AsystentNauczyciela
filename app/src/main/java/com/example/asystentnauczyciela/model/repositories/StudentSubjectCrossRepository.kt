package com.example.asystentnauczyciela.model.repositories

import com.example.asystentnauczyciela.model.entities.StudentSubjectCrossDao
import com.example.asystentnauczyciela.model.entities.StudentSubjectCrossRef

class StudentSubjectCrossRepository(private val studentSubjectCrossDao: StudentSubjectCrossDao) {
    fun addStuStudentSubjectCross(studentSubjectCrossRef: StudentSubjectCrossRef) {
        studentSubjectCrossDao.addStudentSubjectCross(studentSubjectCrossRef)
    }

    fun deleteStudentSubjectCrossById(studentId: Number, subjectId: Number) {
        return studentSubjectCrossDao.deleteById(studentId.toString(), subjectId.toString())
    }
}