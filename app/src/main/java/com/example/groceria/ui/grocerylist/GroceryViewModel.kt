package com.example.groceria.ui.grocerylist

import androidx.lifecycle.ViewModel
import com.example.groceria.data.database.entities.GroceryItem
import com.example.groceria.data.repositories.GroceryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel(
    private val repository: GroceryRepository
): ViewModel() {
    fun upsert(item: GroceryItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: GroceryItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllGroceryItems() = repository.getAllGroceryItems()
}