package com.example.taskpresentation.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskdomain.model.Task
import com.example.taskpresentation.R

class TaskAdapter(
    private val tasks: List<Task>
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerTextView.text = tasks[position].title
    }

    override fun getItemCount() = tasks.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerTextView: TextView = itemView.findViewById(R.id.recycler_text_view)
    }

}