package com.example.makenotes

import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListVewHolder(var itemView:View) : RecyclerView.ViewHolder(itemView) {
    var taskTextView = itemView.findViewById<TextView>(R.id.taskview_task_id)
}
