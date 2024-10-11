package com.example.adminpanelfoodieworld

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
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
            val intent = Intent(this,addMenu::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener {
            val intent = Intent(this,profile::class.java)
            startActivity(intent)
        }

        binding.addAllMenu.setOnClickListener {
            val intent = Intent(this,allItemActivity::class.java)
            startActivity(intent)
        }

        binding.orderStatus.setOnClickListener {
            val intent = Intent(this,outForDelivery::class.java)
            startActivity(intent)
        }

        binding.CreateUser.setOnClickListener {
            val intent = Intent(this,newUser::class.java)
            startActivity(intent)
        }
        binding.pendingorder.setOnClickListener {
            val intent = Intent(this,pendingOrder::class.java)
            startActivity(intent)
        }
    }
}