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

class SubjectsListViewModel(application: Application) : AndroidViewModel(application) {
    private val subjectRepository: SubjectRepository

    init {
        val subjectDao = TeacherAssistantDatabase.getDatabase(application).subjectDao()
        subjectRepository = SubjectRepository(subjectDao)
    }

    fun getAllSubjects(): LiveData<List<Subject>> {
        return subjectRepository.getAllSubjects
    }

    fun deleteSubjectById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.deleteSubjectById(id)
        }
    }
}