package com.example.todolistapp.presentation.item.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigmacamp.myviewmodel.ResourceState
import com.example.todolistapp.entity.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FormFragmentViewModel : ViewModel(){
    private var _isItemValid = MutableLiveData<ResourceState>()

    val isItemValid: LiveData<ResourceState>
        get() {
            return _isItemValid
        }

    fun inputItemValidation(item: Item) {
        GlobalScope.launch {
            _isItemValid.postValue(ResourceState.loading())
            delay(3000)
            if (!item.date.isNullOrBlank() && !item.name.isNullOrBlank() && item.quantity > 0 && !item.note.isNullOrBlank()) {
                _isItemValid.postValue(ResourceState.success(true))
            } else {
                _isItemValid.postValue(ResourceState.fail("Input data cannot empty"))
            }
        }
    }
}