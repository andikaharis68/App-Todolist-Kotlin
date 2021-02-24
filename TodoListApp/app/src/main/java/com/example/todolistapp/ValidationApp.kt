package com.example.todolistapp

import android.text.Editable

object ValidationApp {
    fun validationEditable(editable: Editable) = !editable.toString().isNullOrEmpty()
}