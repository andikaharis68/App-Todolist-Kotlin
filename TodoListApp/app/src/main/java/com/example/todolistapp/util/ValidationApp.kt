package com.example.todolistapp.util

import android.text.Editable

object ValidationApp {
    fun validationEditable(editable: Editable) = !editable.toString().isNullOrEmpty()
}