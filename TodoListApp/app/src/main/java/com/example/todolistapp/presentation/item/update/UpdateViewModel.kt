package com.example.todolistapp.presentation.item.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.entity.Item
import com.example.todolistapp.repositories.SimpleRepository

class UpdateViewModel(private val repository: SimpleRepository) : ViewModel() {
    private var _updateStatus = MutableLiveData<Item>()
    val updateStatus: LiveData<Item>
        get() {
            return _updateStatus
        }

    fun onUpdate(item: Item) {
        val itemUpdated = repository.update(item)
        _updateStatus.value = itemUpdated
    }
}