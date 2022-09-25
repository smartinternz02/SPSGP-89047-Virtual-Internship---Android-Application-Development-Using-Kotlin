package com.example.groceria.other

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceria.R
import com.example.groceria.data.database.entities.GroceryItem
import com.example.groceria.ui.grocerylist.GroceryViewModel
import com.google.android.material.snackbar.Snackbar

class GroceryItemAdapter(
    var items: List<GroceryItem>,
    private val viewModel: GroceryViewModel
): RecyclerView.Adapter<GroceryItemAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAmount : TextView = itemView.findViewById(R.id.tvAmount)
        val ivDelete : ImageView = itemView.findViewById(R.id.ivDelete)
        val ivPlus : ImageView = itemView.findViewById(R.id.ivPlus)
        val ivMinus : ImageView = itemView.findViewById(R.id.ivMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val curGroceryItem = items[position]

        holder.tvName.text = curGroceryItem.name
        holder.tvAmount.text = "${curGroceryItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(curGroceryItem)
            val snackBar = Snackbar.make(it,"Item Deleted!",Snackbar.LENGTH_LONG).apply { setAction("UNDO"){
                viewModel.upsert(curGroceryItem)
            } }
            snackBar.setTextColor(Color.WHITE)
            snackBar.setActionTextColor(Color.WHITE)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.BLACK)
            snackBar.show()
        }

        holder.ivPlus.setOnClickListener {
            curGroceryItem.amount++
            viewModel.upsert(curGroceryItem)
        }

        holder.ivMinus.setOnClickListener {
            if (curGroceryItem.amount > 0) {
                curGroceryItem.amount--
                viewModel.upsert(curGroceryItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}