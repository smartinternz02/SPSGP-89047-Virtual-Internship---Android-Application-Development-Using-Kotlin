package com.example.groceria

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceria.data.database.GroceryDatabase
import com.example.groceria.data.database.entities.GroceryItem
import com.example.groceria.data.repositories.GroceryRepository
import com.example.groceria.databinding.ActivityGroceryBinding
import com.example.groceria.other.GroceryItemAdapter
import com.example.groceria.ui.grocerylist.AddDialogListener
import com.example.groceria.ui.grocerylist.AddGroceryItemDialog
import com.example.groceria.ui.grocerylist.GroceryViewModel
import com.example.groceria.ui.grocerylist.GroceryViewModelFactory


class GroceryActivity : AppCompatActivity() {

    lateinit var viewModel: GroceryViewModel
    lateinit var binding : ActivityGroceryBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroceryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val database = GroceryDatabase(this)
        val repository = GroceryRepository(database)
        val factory = GroceryViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)

        val adapter = GroceryItemAdapter(listOf(), viewModel)

        binding.rvGroceryItems.layoutManager = LinearLayoutManager(this)
        binding.rvGroceryItems.adapter = adapter

        viewModel.getAllGroceryItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })



        binding.fab.setOnClickListener {
            AddGroceryItemDialog(this,
            object : AddDialogListener {
                override fun onAddButtonClicked(item: GroceryItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }

}