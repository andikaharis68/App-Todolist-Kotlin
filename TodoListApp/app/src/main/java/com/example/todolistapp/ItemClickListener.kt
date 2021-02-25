package com.example.todolistapp

import com.example.todolistapp.entity.Item

interface ItemClickListener {
    fun onDelete(item: Item)
}