package com.example.adminpanelfoodieworld

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivityMainBinding
import com.example.adminpanelfoodieworld.model.OrderDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var completeOrderReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.addMenu.setOnClickListener {
            val intent = Intent(this, addMenu::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener {
            val intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        binding.addAllMenu.setOnClickListener {
            val intent = Intent(this, allItemActivity::class.java)
            startActivity(intent)
        }

        binding.orderStatus.setOnClickListener {
            val intent = Intent(this, outForDelivery::class.java)
            startActivity(intent)
        }

        binding.CreateUser.setOnClickListener {
            val intent = Intent(this, newUser::class.java)
            startActivity(intent)
        }
        binding.pendingorder.setOnClickListener {
            val intent = Intent(this, pendingOrder::class.java)
            startActivity(intent)
        }
        pendingorders()

        completedOrders()

        entireEarning()
    }

    private fun entireEarning() {
        var listOfTotalPay = mutableListOf<Int>()
        completeOrderReference = FirebaseDatabase.getInstance().reference.child("CompletedOrder")
        completeOrderReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for( orderSnapShot in snapshot.children){
                    var completeOrder = orderSnapShot.getValue(OrderDetails::class.java)
                    completeOrder?.totalPrice?.replace("$","")?.toIntOrNull()
                        ?.let { i ->
                            listOfTotalPay.add(i)
                        }
                }
                binding.entireEarning.text = listOfTotalPay.sum().toString()+"$"
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun completedOrders() {
        val completedOrderReference = database.reference.child("CompletedOrder")
        var completedOrders = 0
        completedOrderReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                completedOrders = snapshot.childrenCount.toInt()
                binding.ordersCompleted.text = completedOrders.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun pendingorders() {
        database = FirebaseDatabase.getInstance()
        val pendingOrderReference = database.reference.child("Order Details")
        var PendingOrderCount = 0
        pendingOrderReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                PendingOrderCount = snapshot.childrenCount.toInt()
                binding.pendingOrder.text = PendingOrderCount.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}