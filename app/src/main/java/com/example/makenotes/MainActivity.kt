package com.example.makenotes

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() ,TodoListAdapter.TodoListClickListener{
    lateinit var recyclerView: RecyclerView
    private var tag = "myActivity"
    lateinit var todoLists:List<String>
    companion object{
        const val  INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE =7400
    }
    var listDataManager:ListDataManager = ListDataManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        var lists = listDataManager.readLists()
        recyclerView = findViewById(R.id.recycler_view_id)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TodoListAdapter(lists,this)


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
             showCreateTodoListDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == LIST_DETAIL_REQUEST_CODE)
        {
            data?.let {
                val list = data.getParcelableExtra<TaskList>(INTENT_LIST_KEY)
                listDataManager.saveList(list!!)
                updateLists()
            }
        }
    }

    private fun updateLists() {
         val lists = listDataManager.readLists()
        recyclerView.adapter = TodoListAdapter(lists,this)
    }

    private fun showCreateTodoListDialog()
    {
        var dialogTitle = getString(R.string.dialog_title)
        var positiveButtonTitle = getString(R.string.positive_button_title)
        var myDialog = AlertDialog.Builder(this)
        var todoTitleEditeText = EditText(this)
        todoTitleEditeText.inputType = InputType.TYPE_CLASS_TEXT
        myDialog.setTitle(dialogTitle)
        myDialog.setView(todoTitleEditeText)
        myDialog.setPositiveButton(positiveButtonTitle) {
            dialog,_ ->
                var adapter = recyclerView.adapter as TodoListAdapter
                var list = TaskList(todoTitleEditeText.text.toString())
                listDataManager.saveList(list)
                adapter.addList(list)
                dialog.dismiss()
                showTaskListItems(list)

        }
        myDialog.create().show()

    }

    private fun showTaskListItems(list: TaskList)
    {
        var taskListItem = Intent(this,DetailActivity::class.java)
        taskListItem.putExtra(INTENT_LIST_KEY,list)
        Log.d(tag,"------------ shubham -----------------------------------------------------" )
        startActivityForResult(taskListItem, LIST_DETAIL_REQUEST_CODE)
    }

    override fun listItemClicked(list: TaskList) {
         showTaskListItems(list)
    }
}