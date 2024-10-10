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

class outForDelivery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding : ActivityOutForDeliveryBinding by lazy {
            ActivityOutForDeliveryBinding.inflate(layoutInflater)
        }
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

        val customerName = arrayListOf("vaishnavi","somya","tanya","trisha")
        val moneyStates = arrayListOf("recevied","notRecevied","notRecevied","pending")
        val adapter = deliveryAdapter(customerName,moneyStates)
        binding.deliveryList.adapter = adapter
        binding.deliveryList.layoutManager = LinearLayoutManager(this)
    }
}