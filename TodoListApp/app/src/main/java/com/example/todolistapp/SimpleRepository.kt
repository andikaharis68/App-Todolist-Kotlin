package com.example.todolistapp

import com.example.todolistapp.entity.Item

interface SimpleRepository {
    fun add(item: Item)
    fun delete(item: Item)
    fun list(): List<Item>
}