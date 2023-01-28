package com.example.asystentnauczyciela.viewmodel.students

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.database.AsystentNauczycielaDatabase
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.model.entities.StudentZajecia
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import com.example.asystentnauczyciela.model.repositories.StudentZajeciaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignStudentToSubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val studentRepository: StudentRepository
    private val studentZajeciaRepository: StudentZajeciaRepository

    init {
        val database = AsystentNauczycielaDatabase.getDatabase(application)
        studentRepository = StudentRepository(database.studentDao())
        studentZajeciaRepository =
            StudentZajeciaRepository(database.studentSubjectCrossDao())
    }

    fun getUnEnrolledStudentsList(subjectId: Number): LiveData<List<Student>> {
        return studentRepository.getUnEnrolledStudentsList(subjectId)
    }

    fun addEnrollment(studentZajecia: StudentZajecia) {
        viewModelScope.launch(Dispatchers.IO) {
            studentZajeciaRepository.addStuStudentSubjectCross(studentZajecia)
        }
    }
}