package com.example.groceria.ui.grocerylist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.groceria.R
import com.example.groceria.data.database.entities.GroceryItem

class AddGroceryItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_grocery_item)

        val tvAdd = findViewById<TextView>(R.id.tvAdd)
        val tvCancel = findViewById<TextView>(R.id.tvCancel)
        val etName = findViewById<EditText>(R.id.etName)
        val etAmount = findViewById<EditText>(R.id.etAmount)


        tvAdd?.setOnClickListener {
            val name : String = etName?.text.toString()
            val amount : Int = etAmount?.text.toString().toInt()

            if(name.isEmpty()) {
                Toast.makeText(context, "Please enter the name of the item", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val item = GroceryItem(name, amount)
                addDialogListener.onAddButtonClicked(item)
                dismiss()
                Toast.makeText(context,"Item added successfully!",Toast.LENGTH_SHORT).show()
            }
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}