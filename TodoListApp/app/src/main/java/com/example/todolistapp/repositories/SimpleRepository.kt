package com.example.todolistapp.repositories

import com.example.todolistapp.entity.Item

interface SimpleRepository {
    fun add(item: Item) : Item
    fun delete(item: Item) : Boolean
    fun list(): List<Item>
    fun update(item: Item): Item
}