package com.example.todolistapp.repositories

import com.example.todolistapp.entity.Item

class ItemRepository : SimpleRepository {
    companion object {
        var items : MutableList<Item> = mutableListOf(
                Item("20-02-2021", "Meat", 12, "oke"),
                Item("11-03-2021", "Koll", 10, "aman"),
                Item("06-04-2021", "Carrot", 8, "sehat")
        )
    }

    override fun add(item: Item) {
        items.add(item)
    }

    override fun delete(item: Item) {
        val itemPos = items.indexOf(item)
        items.removeAt(itemPos)
    }

    override fun list(): List<Item> = items

}