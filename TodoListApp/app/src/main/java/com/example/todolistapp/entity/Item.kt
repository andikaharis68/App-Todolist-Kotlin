package com.example.todolistapp.entity

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import com.example.todolistapp.util.ValidationApp

//
//import java.util.*
//
data class Item(var id:String = "", var date:String, var name : String, var quantity : Int, var note:String) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!
    ) {
    }

    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(id)
        p0?.writeString(date)
        p0?.writeString(name)
        p0?.writeString(quantity.toString())
        p0?.writeString(note)
    }

    companion object CREATOR : Parcelable.Creator<Item> {

        fun quantityCheck(editable: Editable): Int{
            return if (ValidationApp.validationEditable(editable)){
                 editable.toString().toInt()
            } else {
                0
            }
        }

        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
