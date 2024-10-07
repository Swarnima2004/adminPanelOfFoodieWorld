package com.example.adminpanelfoodieworld

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodieworld.adapter.additemAdapter
import com.example.adminpanelfoodieworld.databinding.ActivityAllItemBinding

class allItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding : ActivityAllItemBinding by lazy{
            ActivityAllItemBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val menuFoodName = listOf("Burger","Rasmalai","Panner Tikka","Gulab Jamun","Burger","Rasmalai","Panner Tikka","Gulab Jamun")
        val menuFoodPrice = listOf("$5","$10","$90","$23","$5","$10","$90","$23")
        val menuImage = listOf(
            R.drawable.burger,
            R.drawable.ras,
            R.drawable.pannertikka,
            R.drawable.gulab_jamun,
            R.drawable.burger,
            R.drawable.ras,
            R.drawable.pannertikka,
            R.drawable.gulab_jamun
        )
        binding.backButtom.setOnClickListener {
            finish()
        }
        val adapter = additemAdapter(ArrayList(menuFoodName),
            ArrayList(menuFoodPrice), ArrayList
        (menuImage)
        )
        binding.allItemRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.allItemRecyclerView.adapter = adapter
    }
}