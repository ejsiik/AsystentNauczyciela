package com.example.asystentnauczyciela.viewmodel.subjects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.database.TeacherAssistantDatabase
import com.example.asystentnauczyciela.model.entities.Subject
import com.example.asystentnauczyciela.model.repositories.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectAddViewModel(application: Application) : AndroidViewModel(application) {
    private val getAllSubjects: LiveData<List<Subject>>
    private val subjectRepository: SubjectRepository

    init {
        val subjectDao = TeacherAssistantDatabase.getDatabase(application).subjectDao()
        subjectRepository = SubjectRepository(subjectDao)
        getAllSubjects = subjectRepository.getAllSubjects
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.addSubject(subject)
        }
    }
}