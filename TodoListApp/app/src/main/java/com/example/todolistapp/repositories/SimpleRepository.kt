package com.example.todolistapp.repositories

import com.example.todolistapp.entity.Item

interface SimpleRepository {
    fun add(item: Item)
    fun delete(item: Item)
    fun list(): List<Item>
}