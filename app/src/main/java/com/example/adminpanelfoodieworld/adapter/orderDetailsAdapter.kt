package com.example.adminpanelfoodieworld.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminpanelfoodieworld.databinding.OrderDetailsItemsBinding


class orderDetailsAdapter(
    private val context: Context,
    private var foodName: ArrayList<String>,
    private var foodImage: ArrayList<String>,
    private var foodQuantities: ArrayList<Int>,
    private var foodPrices: ArrayList <String>
) : RecyclerView.Adapter<orderDetailsAdapter.OrderDetailsViewHolder>() {
    inner class OrderDetailsViewHolder(private val binding: OrderDetailsItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
          binding.apply {
              foodname.text = foodName[position]
              foodQuantity.text = foodQuantities[position].toString()
              var uriString = foodImage[position]
              var uri = Uri.parse(uriString)
              Glide.with(context).load(uri).into(FoodPics)
              foodPrice.text = foodPrices[position]

          }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
       val binding = OrderDetailsItemsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return OrderDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int = foodName.size

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
       holder.bind(position)
    }
}