package com.example.asystentnauczyciela.viewmodel.students

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.database.AsystentNauczycielaDatabase
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsListViewModel(application: Application) : AndroidViewModel(application) {
    private val studentRepository: StudentRepository

    init {
        val studentDao = AsystentNauczycielaDatabase.getDatabase(application).studentDao()
        studentRepository = StudentRepository(studentDao)
    }

    fun getAllStudents(): LiveData<List<Student>> {
        return studentRepository.getAllStudents
    }
}