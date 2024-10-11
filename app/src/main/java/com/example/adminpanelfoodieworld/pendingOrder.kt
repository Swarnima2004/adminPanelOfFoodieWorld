package com.example.adminpanelfoodieworld

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodieworld.adapter.PendingItemAdapter
import com.example.adminpanelfoodieworld.adapter.deliveryAdapter
import com.example.adminpanelfoodieworld.databinding.ActivityPendingOrderBinding

class pendingOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding : ActivityPendingOrderBinding by lazy{
            ActivityPendingOrderBinding.inflate(layoutInflater)
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

        val customerName = arrayListOf("vaishnavi","somya","tanya","trisha","vaishnavi","somya","tanya","trisha")
        val quantity = arrayListOf("1","4","3","8","1","4","3","8")
        val foodimage = arrayListOf(
            R.drawable.burger,
            R.drawable.ras,
            R.drawable.pannertikka,
            R.drawable.gulab_jamun,
            R.drawable.burger,
            R.drawable.ras,
            R.drawable.pannertikka,
            R.drawable.gulab_jamun
        )

        val adapter = PendingItemAdapter(customerName,quantity,foodimage,this)
        binding.orderList.adapter = adapter
        binding.orderList.layoutManager = LinearLayoutManager(this)

    }
}