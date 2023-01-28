package com.example.asystentnauczyciela.viewmodel.subjects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.database.AsystentNauczycielaDatabase
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import com.example.asystentnauczyciela.model.repositories.StudentSubjectCrossRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val studentRepository: StudentRepository
    private val studentSubjectCrossRepository: StudentSubjectCrossRepository

    init {
        val database = AsystentNauczycielaDatabase.getDatabase(application)
        studentRepository = StudentRepository(database.studentDao())
        studentSubjectCrossRepository =
            StudentSubjectCrossRepository(database.studentSubjectCrossDao())
    }

    fun getEnrolledStudentsList(subjectId: Number): LiveData<List<Student>> {
        return studentRepository.getEnrolledStudentsList(subjectId)
    }
}