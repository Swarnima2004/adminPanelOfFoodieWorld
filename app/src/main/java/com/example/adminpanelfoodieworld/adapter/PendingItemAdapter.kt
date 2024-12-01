package com.example.adminpanelfoodieworld.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminpanelfoodieworld.databinding.PendingitemBinding

class PendingItemAdapter(
    private val context: Context,
    private val CustomerName: MutableList<String>,
    private val Quantity: MutableList<String>,
    private val foodImages: MutableList<String>,
    private val Itemclicked: onItemClick

) : RecyclerView.Adapter<PendingItemAdapter.PendingItemViewHolder>() {
interface onItemClick{
    fun onItemClickListener(position: Int)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingItemViewHolder {
        val binding = PendingitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PendingItemViewHolder(binding)
    }

    override fun getItemCount(): Int = CustomerName.size

    override fun onBindViewHolder(holder: PendingItemViewHolder, position: Int) {
        holder.bind(position)
    }
    inner class PendingItemViewHolder(private val binding: PendingitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var Accepted = false


        @SuppressLint("CheckResult")
        fun bind(position: Int) {
            binding.apply {
                nameOfCustomer.text = CustomerName[position]
                OrderPending.text = Quantity[position]
                var uriString = foodImages[position]
                var uri = Uri.parse(uriString)
               Glide.with(context).load(uri).into(FoodPic)

                Acceptbtn.apply {
                    if (!Accepted) {
                        text = "Accept"

                    } else {
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if (!Accepted) {
                            text = "Dispatch"
                            Accepted = true
                            showTheToast("YOUR ORDER IS ACCEPTED")
                        } else {
                            CustomerName.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showTheToast("ORDER IS DISPATCH")
                        }
                    }
                }
                itemView.setOnClickListener {
                    Itemclicked.onItemClickListener(position)
                }
            }

        }

        private fun showTheToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }


}
