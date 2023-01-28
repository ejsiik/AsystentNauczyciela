package com.example.asystentnauczyciela.view.subjects

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
import com.example.asystentnauczyciela.model.entities.Student
import com.example.asystentnauczyciela.view.adapters.RecyclerViewInterface
import com.example.asystentnauczyciela.view.adapters.SubjectStudentListAdapter
import com.example.asystentnauczyciela.viewmodel.subjects.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_subject.view.*

class SubjectFragment : Fragment(), RecyclerViewInterface {

    companion object {
        fun newInstance() = SubjectFragment()
    }

    private var studentsList: List<Student>? = null
    private val args: SubjectFragmentArgs by navArgs()

    private lateinit
    var viewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subject, container, false)

        val recyclerView = view.subject_student_list
        val adapter = SubjectStudentListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this)[SubjectViewModel::class.java]
        viewModel.getEnrolledStudentsList(args.subjectId)
            .observe(viewLifecycleOwner, Observer { students ->
                adapter.setData(students)
                studentsList = students
            })

        view.navigate_to_assing_student.setOnClickListener {
            val action =
                SubjectFragmentDirections.actionSubjectFragmentToAssignStudentToSubjectFragment(
                    args.subjectId,
                    args.subjectName
                )
            findNavController().navigate(action)
        }

        return view
    }

    override fun onItemCLick(id: Int) {
        var fullName = ""
        if (studentsList != null) {
            val student = studentsList!!.find { it.id.toInt() == id.toInt() }
            fullName = "${student?.name} ${student?.surname}"
        }

        val action = SubjectFragmentDirections.actionSubjectFragmentToStudentsMarksFragment(
            args.subjectId, id, fullName
        )
        findNavController().navigate(action)
    }

}