package com.example.todolistapp

import androidx.lifecycle.ViewModel
import com.example.todolistapp.entity.Item

class MainActivityViewModel : ViewModel() {
    var items : MutableList<Item> = mutableListOf(
        Item("20-02-2021", "Meat", 12, "oke"),
        Item("11-03-2021", "Koll", 10, "aman"),
        Item("06-04-2021", "Carrot", 8, "sehat")
    )

    fun addItem(item: Item){
        items.add(item)
    }
}



