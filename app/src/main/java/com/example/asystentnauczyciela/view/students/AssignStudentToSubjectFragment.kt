package com.example.asystentnauczyciela.view.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.StudentZajecia
import com.example.asystentnauczyciela.view.adapters.StudentToZajeciaAdapter
import com.example.asystentnauczyciela.view.adapters.RecyclerViewInterface
import com.example.asystentnauczyciela.viewmodel.students.AssignStudentToSubjectViewModel
import kotlinx.android.synthetic.main.fragment_student_to_zajecia.view.*

class AssignStudentToSubjectFragment : Fragment(), RecyclerViewInterface {

    companion object {
        fun newInstance() = AssignStudentToSubjectFragment()
    }

    private val args: AssignStudentToSubjectFragmentArgs by navArgs()

    private lateinit var viewModel: AssignStudentToSubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_to_zajecia, container, false)

        val recyclerView = view.assign_student_to_subject_recyclerview
        val adapter = StudentToZajeciaAdapter(this, true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this)[AssignStudentToSubjectViewModel::class.java]
        viewModel.getUnEnrolledStudentsList(args.subjectId)
            .observe(viewLifecycleOwner, Observer { students ->
                adapter.setData(students)
            })

        return view
    }

    override fun onItemCLick(id: Int) {
        viewModel.addEnrollment(StudentZajecia(id, args.subjectId))
        findNavController().navigateUp()
    }
}