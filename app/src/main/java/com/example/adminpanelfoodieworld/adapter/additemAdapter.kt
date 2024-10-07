package com.example.adminpanelfoodieworld.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfoodieworld.databinding.ItemBinding

class additemAdapter(private val MenuItemName:ArrayList<String>, private val MenuItemPrice:ArrayList<String>,
                     private val MenuItemImage:ArrayList<Int>):RecyclerView.Adapter<additemAdapter.AddItemViewHolder>() {
    private val itemQuantities = IntArray(MenuItemName.size){1}

    inner class AddItemViewHolder(private val binding: ItemBinding): RecyclerView.ViewHolder(binding.root){
     fun bind (position:Int){
         binding.apply {
             FoodName.text= MenuItemName[position]
             FoodPrice.text= MenuItemPrice[position]
             foodImage.setImageResource(MenuItemImage[position])
             minusbtn.setOnClickListener{
                 decreaseQuantity(position)
             }
             DeleteButton.setOnClickListener {
                 val itemPosition = adapterPosition
                 if(itemPosition != RecyclerView.NO_POSITION){
                     DeleteItem(itemPosition)
                 }

             }

             addbtn.setOnClickListener {
                 increaseQuantity(position)
             }


         }
     }
        private fun decreaseQuantity(position :Int){
            if(itemQuantities[position] >1){
                itemQuantities[position]--
                binding.MenuItenQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position :Int){
            if(itemQuantities[position] <10){
                itemQuantities[position]++
                binding.MenuItenQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun DeleteItem(position : Int){
            MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, MenuItemName.size)
        }
         }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddItemViewHolder(binding)
    }

    override fun getItemCount(): Int = MenuItemName.size

    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }
}