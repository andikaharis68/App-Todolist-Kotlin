package com.example.todolistapp.presentation.item.home

import com.example.todolistapp.entity.Item

interface ItemClickListener {
    fun onDelete(item: Item)
    fun onUpdate(item: Item)
}