package com.example.asystentnauczyciela.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.Student
import kotlinx.android.synthetic.main.student_row.view.*

class SubjectStudentListAdapter(private val recyclerViewInterface: RecyclerViewInterface?) :
    RecyclerView.Adapter<SubjectStudentListAdapter.StudentsListHolder>() {

    var studentList = emptyList<Student>()

    class StudentsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsListHolder {
        return StudentsListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentsListHolder, position: Int) {
        val currentItem = studentList[position]
        holder.itemView.student_name.text = currentItem.name
        holder.itemView.student_surname.text = currentItem.surname
        holder.itemView.student_album_number.text = currentItem.albumNumber

        holder.itemView.setOnClickListener {
            recyclerViewInterface?.onItemCLick(currentItem.id)
        }

        holder.itemView.delete_student_button.setOnClickListener {
            recyclerViewInterface?.onDeleteCLick(currentItem.id)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setData(students: List<Student>) {
        this.studentList = students
        notifyDataSetChanged()
    }
}