package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.entities.Mark
import com.example.asystentnauczyciela.model.entities.MarkDao

class MarkRepository(private val markDao: MarkDao) {
    fun addMark(mark: Mark) {
        markDao.addMark(mark)
    }

    fun getSubjectStudentMarks(subjectId: Int, studentId: Number): LiveData<List<Mark>> {
        return markDao.getSubjectStudentMarks(subjectId.toString(), studentId.toString())
    }
}