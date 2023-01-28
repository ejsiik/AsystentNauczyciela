package com.example.asystentnauczyciela.viewmodel.subjects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.database.AsystentNauczycielaDatabase
import com.example.asystentnauczyciela.model.entities.Subject
import com.example.asystentnauczyciela.model.repositories.SubjectRepository

class SubjectsListViewModel(application: Application) : AndroidViewModel(application) {
    private val subjectRepository: SubjectRepository

    init {
        val subjectDao = AsystentNauczycielaDatabase.getDatabase(application).subjectDao()
        subjectRepository = SubjectRepository(subjectDao)
    }

    fun getAllSubjects(): LiveData<List<Subject>> {
        return subjectRepository.getAllSubjects
    }
}