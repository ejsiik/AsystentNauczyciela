package com.example.asystentnauczyciela.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.entities.Mark
import kotlinx.android.synthetic.main.mark_row.view.*

class MarksAdapter(private val recyclerViewInterface: RecyclerViewInterface?) :
    RecyclerView.Adapter<MarksAdapter.MarksListHolder>() {

    private var marksList = emptyList<Mark>()

    class MarksListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarksListHolder {
        return MarksListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.mark_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarksListHolder, position: Int) {
        val currentItem = marksList[position]
        holder.itemView.mark.text = currentItem.value.toString()
        holder.itemView.elementName.text = currentItem.evaluatedItem

        holder.itemView.delete_mark_button.setOnClickListener {
            recyclerViewInterface?.onDeleteCLick(currentItem.id)
        }
    }

    override fun getItemCount(): Int {
        return marksList.size
    }

    fun setData(marks: List<Mark>) {
        this.marksList = marks
        notifyDataSetChanged()
    }
}