package com.example.asystentnauczyciela.viewmodel.marks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.database.TeacherAssistantDatabase
import com.example.asystentnauczyciela.model.entities.Mark
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsMarksViewModel(application: Application) : AndroidViewModel(application) {
    private val markRepository: MarkRepository

    init {
        val database = TeacherAssistantDatabase.getDatabase(application)
        markRepository = MarkRepository(database.markDao())
    }

    fun getSubjectStudentMarks(subjectId: Int, studentId: Int): LiveData<List<Mark>> {
        return markRepository.getSubjectStudentMarks(subjectId, studentId)
    }

    fun addMark(mark: Mark) {
        viewModelScope.launch(Dispatchers.IO) {
            markRepository.addMark(mark)
        }
    }

    fun deleteMarkById(markId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            markRepository.deleteMarkById(markId)
        }
    }
}