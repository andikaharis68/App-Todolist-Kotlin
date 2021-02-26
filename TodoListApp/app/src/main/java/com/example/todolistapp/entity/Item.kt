package com.example.todolistapp.entity

import android.text.Editable
import com.example.todolistapp.util.ValidationApp

//
//import java.util.*
//
data class Item(var date:String, var name : String, var quantity : Int, var note:String) {
    companion object{
        fun quantityCheck(editable: Editable): Int{
            return if (ValidationApp.validationEditable(editable)){
                 editable.toString().toInt()
            } else {
                0
            }
        }
    }
}
