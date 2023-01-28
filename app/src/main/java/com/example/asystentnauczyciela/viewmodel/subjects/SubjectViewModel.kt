package com.example.asystentnauczyciela.viewmodel.subjects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.database.AsystentNauczycielaDatabase
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import com.example.asystentnauczyciela.model.repositories.StudentZajeciaRepository

class SubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val studentRepository: StudentRepository
    private val studentZajeciaRepository: StudentZajeciaRepository

    init {
        val database = AsystentNauczycielaDatabase.getDatabase(application)
        studentRepository = StudentRepository(database.studentDao())
        studentZajeciaRepository =
            StudentZajeciaRepository(database.studentSubjectCrossDao())
    }

    fun getEnrolledStudentsList(subjectId: Number): LiveData<List<Student>> {
        return studentRepository.getEnrolledStudentsList(subjectId)
    }
}