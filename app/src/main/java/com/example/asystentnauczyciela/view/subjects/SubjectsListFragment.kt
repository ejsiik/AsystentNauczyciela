package com.example.asystentnauczyciela.view.subjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view.adapters.RecyclerViewInterface
import com.example.asystentnauczyciela.view.adapters.SubjectAdapter
import com.example.asystentnauczyciela.viewmodel.subjects.SubjectsListViewModel
import kotlinx.android.synthetic.main.fragment_subjects_list.view.*

class SubjectsListFragment : Fragment(), RecyclerViewInterface {

    companion object {
        fun newInstance() = SubjectsListFragment()
    }

    private lateinit var viewModel: SubjectsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subjects_list, container, false)

        val recyclerView = view.subjectsRecyclerView
        val adapter = SubjectAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(SubjectsListViewModel::class.java)
        viewModel.getAllSubjects().observe(viewLifecycleOwner, Observer { subjects ->
            adapter.setData(subjects)
        })

        view.addSubjectButton.setOnClickListener {
            findNavController().navigate(R.id.subjectAddFragment)
        }
        return view
    }

    override fun onItemCLick(id: Int) {

    }
}