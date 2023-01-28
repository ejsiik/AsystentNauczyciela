package com.example.asystentnauczyciela.view.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.Mark
import com.example.asystentnauczyciela.viewmodel.marks.StudentsMarksViewModel
import kotlinx.android.synthetic.main.fragment_add_mark_dialog.view.*

class AddMarkDialogFragment(
    val viewModel: StudentsMarksViewModel,
    private val args: StudentsMarksFragmentArgs
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_mark_dialog, container, false)

        view.addMarkDialogButton.setOnClickListener {
            val mark = view.markDialogInput.text.toString().toFloat()
            val item = view.markElementDialogInput.text.toString()

            if (checkInput(mark, item)) {
                val mark = Mark(0, args.studentId, args.subjectId, mark, item)
                viewModel.addMark(mark)
                dialog?.cancel()
            } else {
                Toast.makeText(requireContext(), "Wpisz poprawne dane!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return view
    }

    private fun checkInput(mark: Float, item: String): Boolean {
        return item.isNotEmpty() && mark >= 2 && mark <= 5 && mark.mod(0.5f) == 0.0f
    }

}