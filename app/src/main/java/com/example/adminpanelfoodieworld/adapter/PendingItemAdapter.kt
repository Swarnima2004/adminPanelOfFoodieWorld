package com.example.adminpanelfoodieworld.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfoodieworld.databinding.PendingitemBinding

class PendingItemAdapter(private val CustomerName:ArrayList<String>, private val Quantity: ArrayList<String>,
                         private val foodImages: ArrayList<Int>,private val context: Context):RecyclerView.Adapter<PendingItemAdapter.PendingItemViewHolder>() {
   inner class PendingItemViewHolder (private val binding:PendingitemBinding):RecyclerView.ViewHolder(binding.root){
     private var Accepted = false


       fun bind(position: Int) {
          binding.apply{
              nameOfCustomer.text = CustomerName[position]
              OrderPending.text = Quantity[position]
              FoodPic.setImageResource(foodImages[position])

              Acceptbtn.apply{
                  if(!Accepted){
                      text ="Accept"
                  }
                  else{
                      text= "Dispatch"
                  }
                  setOnClickListener{
                      if(!Accepted){
                          text="Dispatch"
                          Accepted = true
                         showTheToast("YOUR ORDER IS ACCEPTED")
                      }else{
                          CustomerName.removeAt(adapterPosition)
                          notifyItemRemoved(adapterPosition)
                          showTheToast("ORDER IS DISPATCH")
                      }
                  }
              }
          }

       }
       private fun showTheToast(message:String){
           Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingItemViewHolder {
        val binding = PendingitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PendingItemViewHolder(binding)
    }

    override fun getItemCount(): Int = CustomerName.size

    override fun onBindViewHolder(holder: PendingItemViewHolder, position: Int) {
        holder.bind(position)
    }
}
