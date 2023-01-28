package com.example.asystentnauczyciela.view.subjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.Subject
import com.example.asystentnauczyciela.viewmodel.subjects.SubjectAddViewModel
import kotlinx.android.synthetic.main.fragment_subject_add.view.*

class SubjectAddFragment : Fragment() {

    companion object {
        fun newInstance() = SubjectAddFragment()
    }

    private lateinit var viewModel: SubjectAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subject_add, container, false)

        viewModel = ViewModelProvider(this).get(SubjectAddViewModel::class.java)

        val spinner = view.spinner

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.days_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

        view.subjectFormSubmitButton.setOnClickListener {
            val name = view.subjectFormName.text.toString()
            val form = view.subjectFormTimeFrom.text.toString()
            val to = view.subjectFormTimeTo.text.toString()
            val dayId = view.spinner.selectedItemId.toInt()
            val dayName = view.spinner.selectedItem.toString()

            if (checkInput(name, dayId, dayName, form, to)) {
                val subject = Subject(0, name, dayId, dayName, form, to)
                viewModel.addSubject(subject)
                Toast.makeText(requireContext(), "Dodano zajecia!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.subjectsListFragment)
            } else {
                Toast.makeText(requireContext(), "Wpisz poprawne dane!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return view
    }

    private fun checkInput(
        name: String,
        dayId: Int,
        dayName: String,
        from: String,
        to: String
    ): Boolean {
        return name.isNotEmpty() && from.isNotEmpty() && to.isNotEmpty() &&
                (from.length == 5 || from.length == 2) && (to.length == 5 || to.length == 2)
    }
}