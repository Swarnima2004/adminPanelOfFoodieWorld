package com.example.adminpanelfoodieworld.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminpanelfoodieworld.databinding.ItemBinding
import com.example.adminpanelfoodieworld.model.AllMenu
import com.google.firebase.database.DatabaseReference

class MenuitemAdapter(
    private var context: Context,
    private var menuList: ArrayList<AllMenu>,
    databaseReference: DatabaseReference,
    private var onDeleteClickListener :(position : Int) -> Unit
) : RecyclerView.Adapter<MenuitemAdapter.AddItemViewHolder>() {
    private val itemQuantities = IntArray(menuList.size) { 1 }

    inner class AddItemViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)

                FoodName.text = menuItem.foodName
                FoodPrice.text = menuItem.foodPrice
                // using glide for fast loading the image from firebase
                Glide.with(context).load(uri).into(foodImage)


                MenuItenQuantity.text = quantity.toString()
                minusbtn.setOnClickListener {
                    decreaseQuantity(position)
                }
                DeleteButton.setOnClickListener {
                    onDeleteClickListener(position)
                    }



                addbtn.setOnClickListener {
                    increaseQuantity(position)
                }


            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.MenuItenQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.MenuItenQuantity.text = itemQuantities[position].toString()
            }
        }


        private fun DeleteItem(position: Int) {
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, menuList.size)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }
}