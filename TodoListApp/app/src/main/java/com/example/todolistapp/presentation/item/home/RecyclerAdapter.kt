package com.example.todolistapp.presentation.item.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.entity.Item

class RecyclerAdapter(val itemClickListener: ItemClickListener): RecyclerView.Adapter<RecyclerHolder>() {

    var items = ArrayList<Item>()

    //    methode ini dipanggil setiapkali perlu membuat view holder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.card_view, parent, false)
        return RecyclerHolder(itemView, itemClickListener)
    }

    //    method ini untuk distribusi viewholder dengan data
    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    //    Untuk mendapatkan ukuran data
    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newItemList: List<Item>) {
        items.clear()
        items.addAll(newItemList)
        notifyDataSetChanged()
    }
}