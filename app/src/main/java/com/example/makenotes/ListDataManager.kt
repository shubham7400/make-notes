package com.example.makenotes

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager(private val context:Context) {
    fun saveList(list:TaskList)
    {
        var sharedprefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedprefs.putStringSet(list.name,list.tasks.toHashSet())
        sharedprefs.apply()
    }
    fun readLists(): ArrayList<TaskList>{
        var sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        var contents = sharedPrefs.all
        var taskLists = ArrayList<TaskList>()
        for (taskList in contents)
        {
            var taskItems = ArrayList(taskList.value as HashSet<String>)
            var list =  TaskList(taskList.key,taskItems)
            taskLists.add(list)
        }
        return taskLists
    }
}