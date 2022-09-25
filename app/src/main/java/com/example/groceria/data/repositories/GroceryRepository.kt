package com.example.groceria.data.repositories

import com.example.groceria.data.database.GroceryDatabase
import com.example.groceria.data.database.entities.GroceryItem

class GroceryRepository(
    private val db: GroceryDatabase
) {
    suspend fun upsert(item: GroceryItem) = db.getGroceryDao().upsert(item)

    suspend fun delete(item: GroceryItem) = db.getGroceryDao().delete(item)

    fun getAllGroceryItems() = db.getGroceryDao().getAllGroceryItems()
}