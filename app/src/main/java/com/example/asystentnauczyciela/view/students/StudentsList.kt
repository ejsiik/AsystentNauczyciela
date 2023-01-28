package com.example.asystentnauczyciela.view.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view.adapters.RecyclerViewInterface
import com.example.asystentnauczyciela.view.adapters.StudentsAdapter
import com.example.asystentnauczyciela.viewmodel.students.StudentsListViewModel
import kotlinx.android.synthetic.main.fragment_students.view.*

class StudentsList : Fragment(), RecyclerViewInterface {

    companion object {
        fun newInstance() = StudentsList()
    }

    private lateinit var viewModel: StudentsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students, container, false)

        val recyclerView = view.students_recyclerview
        val adapter = StudentsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(StudentsListViewModel::class.java)
        viewModel.getAllStudents().observe(viewLifecycleOwner, Observer { students ->
            adapter.setData(students)
        })


        view.findViewById<Button>(R.id.addStudentButton).setOnClickListener {
            findNavController().navigate(R.id.addStudent)
        }

        return view
    }

    override fun onItemCLick(id: Int) {
    }

    override fun onDeleteCLick(id: Int) {
        viewModel.deleteStudentById(id)
    }
}