package com.example.makenotes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(var lists: TaskList): RecyclerView.Adapter<TaskListVewHolder>() {
    private  val tag = "MyActivity"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListVewHolder{
         var  view = LayoutInflater.from(parent.context).inflate(R.layout.task_view_holder,parent,false)
        Log.d(tag,"---------------------------------------------------------taskoncreate")
        return TaskListVewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListVewHolder, position: Int) {
        holder.taskTextView.text = lists.tasks[position]
        Log.d(tag,"------------------mogarkar------------------------------------------------------taskonBind")
        holder.taskTextView.text = "shubham mogarkar"
    }

    override fun getItemCount(): Int {
         return lists.tasks.size
        Log.d(tag,"---------------------------------------------------------taskgetitemcount")
    }
}