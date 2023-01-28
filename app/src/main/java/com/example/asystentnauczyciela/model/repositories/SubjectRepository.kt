package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.entities.Subject
import com.example.asystentnauczyciela.model.entities.SubjectDao

class SubjectRepository(private val zajeciaDao: SubjectDao) {
    val getAllSubjects: LiveData<List<Subject>> = zajeciaDao.getAllSubjects()

    fun addSubject(subject: Subject) {
        zajeciaDao.addSubject(subject)
    }
}