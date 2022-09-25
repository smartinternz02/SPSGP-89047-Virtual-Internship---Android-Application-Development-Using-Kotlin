package com.example.groceria.ui.grocerylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.groceria.data.repositories.GroceryRepository

@Suppress("UNCHECKED_CAST")
class GroceryViewModelFactory(
    private val repository: GroceryRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroceryViewModel(repository) as T
    }
}