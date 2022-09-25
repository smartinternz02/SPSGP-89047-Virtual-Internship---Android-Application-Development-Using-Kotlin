package com.example.groceria.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")
data class GroceryItem(

    @ColumnInfo(name = "item_name")
    var name: String,

    @ColumnInfo(name = "item_amount")
    var amount: Int,

){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
