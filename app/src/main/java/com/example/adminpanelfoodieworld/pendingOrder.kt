package com.example.adminpanelfoodieworld

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodieworld.adapter.PendingItemAdapter
import com.example.adminpanelfoodieworld.databinding.ActivityPendingOrderBinding
import com.example.adminpanelfoodieworld.model.OrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pendingOrder : AppCompatActivity(), PendingItemAdapter.onItemClick {
    val binding: ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    private var listOfName: MutableList<String> = mutableListOf()
    private var listOfTotalPrice: MutableList<String> = mutableListOf()
    private var listOfImageFirstOrder: MutableList<String> = mutableListOf()
    private var listOfOrderItem: ArrayList<OrderDetails> = arrayListOf()
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseOrderDetails: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //initialization of database
        database = FirebaseDatabase.getInstance()
        //initialization of daatabase reference
        databaseOrderDetails = database.reference.child("Order Details")

        getOrdersDetails()
        binding.backButtom.setOnClickListener {
            finish()
        }


    }

    private fun getOrdersDetails() {
        //retrieve order details from Firebase Database
        databaseOrderDetails.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (orderSnapshot in snapshot.children) {
                    val orderDetails = orderSnapshot.getValue(OrderDetails::class.java)
                    orderDetails?.let {
                        listOfOrderItem.add(it)
                    }
                }
                addDataToListForRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun addDataToListForRecyclerView() {
        for (orderItem in listOfOrderItem) {
            //add data to respective list for populating the recycler view
            orderItem.userName?.let {
                listOfName.add(it)
            }
            orderItem.totalPrice?.let {
                listOfTotalPrice.add(it)
            }
            orderItem.foodImages?.filterNot {
                it.isEmpty()
            }?.forEach {
                listOfImageFirstOrder.add(it)
            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        binding.orderList.layoutManager = LinearLayoutManager(this)
        val adapter =
            PendingItemAdapter(this, listOfName, listOfTotalPrice, listOfImageFirstOrder, this)
        binding.orderList.adapter = adapter
    }

    override fun onItemClickListener(position: Int) {
        val intent = Intent(this, Order_Detail::class.java)
        val userOrderDetails = listOfOrderItem[position]
        intent.putExtra("UserOrderDetails", userOrderDetails)
        startActivity(intent)
    }

    override fun onItemAcceptClickListener(position: Int) {
        //handle the item accept and update database
        val childItem = listOfOrderItem[position].itemPushKey
        val clickItemOrderReference = childItem?.let {
            database.reference.child("Order Details").child(it)
        }
        clickItemOrderReference?.child("orderAccepted")?.setValue(true)
        updateOrderStatus(position)
    }

    private fun updateOrderStatus(position: Int) {
        //update order acceptance in user's buy history and order details
        val userIdOfClickedItem = listOfOrderItem[position].userUid
        val pushKeyOfItemClicked = listOfOrderItem[position].itemPushKey
        val buyHistoryReference =
            database.reference.child("user").child(userIdOfClickedItem!!).child("BuyHistory")
                .child(pushKeyOfItemClicked!!)
        buyHistoryReference.child("orderAccepted").setValue(true)
        databaseOrderDetails.child(pushKeyOfItemClicked).child("orderAccepted").setValue(true)
    }

    override fun onDispatchItemClickListener(position: Int) {
        //handle dispatch of the item
        val dispatchItemKey = listOfOrderItem[position].itemPushKey
        val dispatchItemOrderReference = database.reference.child("CompletedOrder").child(dispatchItemKey!!)
        dispatchItemOrderReference.setValue(listOfOrderItem[position])
            .addOnSuccessListener {
                deleteTheItemFromOrderDetails(dispatchItemKey)
            }

    }

    private fun deleteTheItemFromOrderDetails(dispatchItemKey: String) {
        val orderDetailItemReference =
            database.reference.child("Order Details").child(dispatchItemKey)
        orderDetailItemReference.removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Order Is Dispatched", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Order Failed To Dispatch", Toast.LENGTH_SHORT).show()
            }
    }
}