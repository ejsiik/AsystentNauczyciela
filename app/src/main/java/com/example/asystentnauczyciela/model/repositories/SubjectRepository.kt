package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.entities.Subject
import com.example.asystentnauczyciela.model.entities.SubjectDao

class SubjectRepository(private val subjectDao: SubjectDao) {
    val getAllSubjects: LiveData<List<Subject>> = subjectDao.getAllSubjects()

    fun addSubject(subject: Subject) {
        subjectDao.addSubject(subject)
    }

    fun deleteSubjectById(subjectId: Int) {
        subjectDao.deleteById(subjectId.toString())
    }
}