package com.example.asystentnauczyciela.view.students

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.viewmodel.students.AddStudentViewModel

class AddStudent : Fragment() {

    companion object {
        fun newInstance() = AddStudent()
    }

    private lateinit var viewModel: AddStudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        viewModel = ViewModelProvider(this).get(AddStudentViewModel::class.java)

        view.findViewById<Button>(R.id.addStudentFormButton).setOnClickListener {
            val name = view.findViewById<TextView>(R.id.nameInput).text.toString()
            val surname = view.findViewById<TextView>(R.id.surnameInput).text.toString()
            val albumNumber =
                view.findViewById<TextView>(R.id.studentAlbumNumberInput).text.toString()

            if (checkInput(name, surname, albumNumber)) {
                val student = Student(0, name, surname, albumNumber)
                viewModel.addStudent(student)
                Toast.makeText(requireContext(), "Dodano studenta!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.students)
            } else {
                Toast.makeText(requireContext(), "Wpisz poprawne dane!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return view
    }

    private fun checkInput(name: String, surname: String, albumNumber: String): Boolean {
        return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && (albumNumber.length == 6)
    }
}