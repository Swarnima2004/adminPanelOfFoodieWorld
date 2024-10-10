package com.example.adminpanelfoodieworld.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfoodieworld.databinding.DeliveryitemBinding

class deliveryAdapter(private val customer:ArrayList<String>,private val moneyStatus:ArrayList<String>):RecyclerView.Adapter<deliveryAdapter.deliveryViewHolder>() {
   inner class deliveryViewHolder(private val binding : DeliveryitemBinding):RecyclerView.ViewHolder(binding.root) {
       fun bind(position: Int) {
           binding.apply{
               customerName.text=customer[position]
               status.text = moneyStatus[position]
               val colorMap = mapOf(
                   "recevied" to Color.GREEN,
                   "notRecevied" to Color.RED,
                   "pending" to Color.GRAY

               )

               status.setTextColor(colorMap[moneyStatus[position]]?:Color.BLACK)
               deliveryStatus.backgroundTintList = ColorStateList.valueOf(colorMap[moneyStatus[position]]?:Color.BLACK )
           }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): deliveryViewHolder {
        val binding = DeliveryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return deliveryViewHolder(binding)
    }

    override fun getItemCount(): Int = customer.size

    override fun onBindViewHolder(holder: deliveryViewHolder, position: Int) {
       holder.bind(position)
    }

}