package com.example.makenotes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(var lists: ArrayList<TaskList>,var clickListener:TodoListClickListener) : RecyclerView.Adapter<TodoListViewHolder>() {
    lateinit var view:View
    private val tag = "myActivity"
    interface TodoListClickListener{
        fun listItemClicked(list: TaskList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
       view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_view_holder, parent,false)
        Log.d(tag,"---------------------------------------------------------todooncreate")
        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.listPositionTextView.text = (position + 1).toString()
        Log.d(tag,"---------------------------------------------------------todoonBind")
        holder.listTitleTextView.text = lists[position].name
        view.setOnClickListener{
            clickListener.listItemClicked(lists[position])
        }
    }

    override fun getItemCount(): Int {
         return lists.size
    }



    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }

}
