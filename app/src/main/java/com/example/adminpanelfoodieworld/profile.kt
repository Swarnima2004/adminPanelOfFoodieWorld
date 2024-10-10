package com.example.adminpanelfoodieworld

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivityMainBinding
import com.example.adminpanelfoodieworld.databinding.ActivityProfileBinding

class profile : AppCompatActivity() {
    private val binding : ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
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

        binding.backButtom.setOnClickListener{
            finish()
        }
        binding.Name.isEnabled = false
        binding.address.isEnabled = false
        binding.number.isEnabled = false
        binding.email.isEnabled = false
        binding.password.isEnabled = false

         var isEnable = false
        binding.changebtn.setOnClickListener {
            isEnable != isEnable

            binding.Name.isEnabled = true
            binding.address.isEnabled = true
            binding.number.isEnabled = true
            binding.email.isEnabled = true
            binding.password.isEnabled = true

            if(isEnable){
                binding.Name.requestFocus()
            }

        }
    }
}