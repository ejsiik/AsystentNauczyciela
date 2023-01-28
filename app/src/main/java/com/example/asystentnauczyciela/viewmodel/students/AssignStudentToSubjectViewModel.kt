package com.example.asystentnauczyciela.viewmodel.students

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.database.TeacherAssistantDatabase
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.model.entities.StudentSubjectCrossRef
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import com.example.asystentnauczyciela.model.repositories.StudentSubjectCrossRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignStudentToSubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val studentRepository: StudentRepository
    private val studentSubjectCrossRepository: StudentSubjectCrossRepository

    init {
        val database = TeacherAssistantDatabase.getDatabase(application)
        studentRepository = StudentRepository(database.studentDao())
        studentSubjectCrossRepository =
            StudentSubjectCrossRepository(database.studentSubjectCrossDao())
    }

    fun getUnEnrolledStudentsList(subjectId: Number): LiveData<List<Student>> {
        return studentRepository.getUnEnrolledStudentsList(subjectId)
    }

    fun addEnrollment(studentSubjectCrossRef: StudentSubjectCrossRef) {
        viewModelScope.launch(Dispatchers.IO) {
            studentSubjectCrossRepository.addStuStudentSubjectCross(studentSubjectCrossRef)
        }
    }
}