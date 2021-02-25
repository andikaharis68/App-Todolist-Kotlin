package com.example.todolistapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.CardViewBinding
import com.example.todolistapp.entity.Item

class RecyclerHolder(view: View, val clickListener: ItemClickListener) : RecyclerView.ViewHolder(view) {

    val binding = CardViewBinding.bind(view)

    fun bind(items: Item) {
        binding.apply {
            cvDate.setText(items.date)
            cvName.setText(items.name)
            cvQty.setText(items.quantity.toString())
            cvNote.setText(items.note)
            btDelete.setOnClickListener {
                clickListener.onDelete(items)
            }
        }
    }
}