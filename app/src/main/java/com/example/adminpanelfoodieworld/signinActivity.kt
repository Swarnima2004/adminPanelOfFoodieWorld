package com.example.adminpanelfoodieworld

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivitySigninBinding

class signinActivity : AppCompatActivity() {
    private val binding : ActivitySigninBinding by lazy{
        ActivitySigninBinding.inflate(layoutInflater)
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

        val locationList = arrayOf("Jaipur","Delhi","Gorakhpur","Agar")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView = binding.locationList
        autoCompleteTextView.setAdapter(adapter)

        binding.signinbtn.setOnClickListener {
            val intent=  Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.alreadyHaveAccount.setOnClickListener {
            val intent=  Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
    }
}