package com.example.asystentnauczyciela.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystentnauczyciela.model.entities.*

@Database(
    entities = [Student::class, Subject::class, StudentSubjectCrossRef::class, Mark::class],
    version = 5,
    exportSchema = false
)
abstract class TeacherAssistantDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun studentSubjectCrossDao(): StudentSubjectCrossDao
    abstract fun markDao(): MarkDao


    companion object {
        @Volatile
        private var INSTANCE: TeacherAssistantDatabase? = null

        fun getDatabase(context: Context): TeacherAssistantDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeacherAssistantDatabase::class.java,
                    "teacher_assistant_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}