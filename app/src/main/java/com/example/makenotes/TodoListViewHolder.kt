package com.example.makenotes

import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class TodoListViewHolder( itemView: View):RecyclerView.ViewHolder(itemView) {
    var itemView = itemView
    var listPositionTextView = itemView.findViewById<TextView>(R.id.item_number_id)
    var listTitleTextView = itemView.findViewById<TextView>(R.id.item_title_id)

}
