package com.example.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.entity.Item

class RecyclerAdapter(items : MutableList<Item>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var viewModel: MainActivityViewModel
    var item = items

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.cv_name)
        var itemQty: TextView = itemView.findViewById(R.id.cv_qty)
        var itemNote: TextView = itemView.findViewById(R.id.cv_note)
        var itemDate: TextView = itemView.findViewById(R.id.cv_date)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_view, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemName.text = item[i].name
        viewHolder.itemQty.text = item[i].quantity.toString()
        viewHolder.itemNote.text = item[i].note
        viewHolder.itemDate.text = item[i].date
    }
}