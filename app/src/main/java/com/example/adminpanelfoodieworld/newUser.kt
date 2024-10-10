package com.example.adminpanelfoodieworld

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivityNewUserBinding

class newUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding : ActivityNewUserBinding by lazy{
            ActivityNewUserBinding.inflate(layoutInflater)
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

    }
}