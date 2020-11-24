package com.example.makenotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    lateinit var list:TaskList
    private  val tag = "MyActivity"
    lateinit var taskListRecyclerView:RecyclerView
    lateinit var addTaskButton:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"------------ shubham -----------------------------------------------------" )
        setContentView(R.layout.activity_detail)
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = list.name
        taskListRecyclerView = findViewById(R.id.task_list_recyclerview_id)
        taskListRecyclerView.layoutManager = LinearLayoutManager(this)

        taskListRecyclerView.adapter = TaskListAdapter(list)
        addTaskButton = findViewById(R.id.add_task_button_id)
        addTaskButton.setOnClickListener{
            showCreatTaskDialog()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)

    }
    private fun showCreatTaskDialog() {
         var taskEditeText =EditText(this)
        taskEditeText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this).setTitle(R.string.task_to_add).setView(taskEditeText).setPositiveButton(R.string.add_task){
            dialog, _ ->
            var task = taskEditeText.text.toString()
            Log.d(tag,"------------------------------------------shubham $task")
            list.tasks.add(task)
            dialog.dismiss()
        }.create().show()

    }
}






