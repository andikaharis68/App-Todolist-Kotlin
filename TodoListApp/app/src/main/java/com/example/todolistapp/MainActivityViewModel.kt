package com.example.todolistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.entity.Item

class MainActivityViewModel(val repository: SimpleRepository) : ViewModel(), ItemClickListener  {
    private var _itemLiveData = MutableLiveData<List<Item>>()

    val itemLiveData: LiveData<List<Item>>
        get() {
            return _itemLiveData
        }

    init {
        loadItemData()
    }

    fun loadItemData() {
        _itemLiveData.value = repository.list()
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    fun addItem(item: Item){
        repository.add(item)
        loadItemData()
    }
}



