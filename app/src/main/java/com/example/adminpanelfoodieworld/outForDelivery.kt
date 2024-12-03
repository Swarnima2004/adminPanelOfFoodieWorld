package com.example.adminpanelfoodieworld

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.adminpanelfoodieworld.adapter.deliveryAdapter
import com.example.adminpanelfoodieworld.databinding.ActivityAddMenuBinding
import com.example.adminpanelfoodieworld.databinding.ActivityOutForDeliveryBinding
import com.example.adminpanelfoodieworld.model.OrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class outForDelivery : AppCompatActivity() {
    val binding : ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    private lateinit var database : FirebaseDatabase
    private var listOfCompleteOrderList : ArrayList<OrderDetails> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.backButtom.setOnClickListener{
            finish()
        }
    // retrieve the food which is completed
        retrieveTheCompletedOrder()

    }

    private fun retrieveTheCompletedOrder() {
        //Initialise firebase database
        database = FirebaseDatabase.getInstance()
        val completeOrderReference = database.reference.child("CompletedOrder")
            .orderByChild("currentTime")
        completeOrderReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               //clear the list filling it with new data
                listOfCompleteOrderList.clear()
                for( orderSnapshot in snapshot.children){
                 val completeOrder = orderSnapshot.getValue(OrderDetails::class.java)
                    completeOrder?.let{
                        listOfCompleteOrderList.add(it)
                    }
                }
                //reverse the list of item to show the latest item first
                listOfCompleteOrderList.reverse()

                setDataInRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setDataInRecyclerView() {
        //initialise the list to hold the customers name and payment status
       val customerName = mutableListOf<String>()
        val moneyStatus = mutableListOf<Boolean>()

        for(order in listOfCompleteOrderList){
            order.userName?.let{
                customerName.add(it)
            }
            moneyStatus.add(order.paymentReceived)
        }

        val adapter = deliveryAdapter(customerName,moneyStatus)
        binding.deliveryList.adapter = adapter
        binding.deliveryList.layoutManager = LinearLayoutManager(this)
    }
}