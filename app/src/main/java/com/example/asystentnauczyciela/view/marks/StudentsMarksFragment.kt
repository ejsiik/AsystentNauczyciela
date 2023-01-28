package com.example.asystentnauczyciela.view.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view.adapters.MarksAdapter
import com.example.asystentnauczyciela.view.adapters.RecyclerViewInterface
import com.example.asystentnauczyciela.viewmodel.marks.StudentsMarksViewModel
import kotlinx.android.synthetic.main.fragment_students_marks.view.*

class StudentsMarksFragment : Fragment(), RecyclerViewInterface {

    companion object {
        fun newInstance() = StudentsMarksFragment()
    }

    private val args: StudentsMarksFragmentArgs by navArgs()

    private lateinit var viewModel: StudentsMarksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_students_marks, container, false)

        val recyclerView = view.studentsMarksRecyclerView
        val adapter = MarksAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this)[StudentsMarksViewModel::class.java]
        viewModel.getSubjectStudentMarks(args.subjectId, args.studentId)
            .observe(viewLifecycleOwner, Observer { marks ->
                adapter.setData(marks)
            })

        view.addMarkButton.setOnClickListener {
            val dialog = AddMarkDialogFragment(viewModel, args)
            dialog.show(parentFragmentManager, "add_mark")
        }

        return view
    }

    override fun onItemCLick(id: Int) {
    }

    override fun onDeleteCLick(id: Int) {
        viewModel.deleteMarkById(id)
    }
}