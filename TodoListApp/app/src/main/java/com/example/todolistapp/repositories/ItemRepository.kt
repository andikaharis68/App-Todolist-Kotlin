package com.example.todolistapp.repositories

import android.util.Log
import com.example.todolistapp.entity.Item
import java.util.*

class ItemRepository : SimpleRepository {
    companion object {
        var items : MutableList<Item> = mutableListOf(
                Item("1","20-02-2021", "Kacang", 12, "Kacang Tanah"),
                Item("2","11-03-2021", "Minyak", 10, "Liter"),
                Item("3","06-04-2021", "Semangka", 8, "Semangka Kuning")
        )
    }

    override fun add(item: Item): Item {
        if (item.id.isNullOrEmpty()) {
            val uuid = UUID.randomUUID().toString()
            val itemWithId = item.copy(id = uuid)
            items.add(itemWithId)
            return itemWithId
        } else {
            items.add(item)
            return item
        }
    }

    override fun delete(item: Item) : Boolean {
        val itemPos = items.indexOf(item)
        items.removeAt(itemPos)
        return true
    }

    override fun list(): List<Item> = items

    override fun update(item: Item): Item {
        var oldItem = items.find {
            it.id == item.id
        }
        oldItem?.let {
            delete(it)
            add(item)
        }
        return item
    }

}