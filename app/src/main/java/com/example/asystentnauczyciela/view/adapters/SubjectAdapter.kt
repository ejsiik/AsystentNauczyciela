package com.example.asystentnauczyciela.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.Subject
import com.example.asystentnauczyciela.view.subjects.SubjectsListFragmentDirections
import kotlinx.android.synthetic.main.subject_row.view.*

class SubjectAdapter(private val recyclerViewInterface: RecyclerViewInterface?) :
    RecyclerView.Adapter<SubjectAdapter.SubjectListHolder>() {
    private var subjectList = emptyList<Subject>()

    class SubjectListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectListHolder {
        return SubjectListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subject_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubjectListHolder, position: Int) {
        val currentItem = subjectList[position]
        holder.itemView.subjectName.text = currentItem.name
        holder.itemView.subjectDayName.text = currentItem.dayName
        holder.itemView.subjectFormTo.text = "(${currentItem.timeFrom} - ${currentItem.timeTo})"
        holder.itemView.setOnClickListener {
            val action = SubjectsListFragmentDirections.actionSubjectsListFragmentToSubjectFragment(
                currentItem.id,
                currentItem.name
            )
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.delete_subject_button.setOnClickListener {
            recyclerViewInterface?.onDeleteCLick(currentItem.id)
        }
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    fun setData(subjects: List<Subject>) {
        this.subjectList = subjects
        notifyDataSetChanged()
    }
}