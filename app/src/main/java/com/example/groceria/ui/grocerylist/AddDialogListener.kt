package com.example.groceria.ui.grocerylist

import com.example.groceria.data.database.entities.GroceryItem

interface AddDialogListener {
    fun onAddButtonClicked(item: GroceryItem)
}