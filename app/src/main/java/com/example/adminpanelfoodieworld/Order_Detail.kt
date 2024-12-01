package com.example.adminpanelfoodieworld

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfoodieworld.adapter.orderDetailsAdapter
import com.example.adminpanelfoodieworld.databinding.ActivityOrderDetailBinding
import com.example.adminpanelfoodieworld.model.OrderDetails

class Order_Detail : AppCompatActivity() {
    private val binding : ActivityOrderDetailBinding by lazy{
        ActivityOrderDetailBinding.inflate(layoutInflater)
    }
    private var username :String? = null
    private var address : String? = null
    private var phoneNumber : String? = null
    private var totalPrice : String? = null
    private  var foodNames : ArrayList<String> = arrayListOf()
    private  var foodImages : ArrayList<String> = arrayListOf()
    private  var foodQuantity : ArrayList<Int> = arrayListOf()
    private  var foodPrices : ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.backBtn.setOnClickListener {
            finish()
        }

        getDataFromIntent()

    }

    private fun getDataFromIntent() {
         val receiveOrderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails
         receiveOrderDetails?. let {
             username = receiveOrderDetails.userName
             foodNames = receiveOrderDetails.foodName as ArrayList<String>
             foodImages = receiveOrderDetails.foodImages as ArrayList<String>
             foodQuantity = receiveOrderDetails.foodQuantities as ArrayList<Int>
             address = receiveOrderDetails.address
             phoneNumber =receiveOrderDetails.phoneNumber
             foodPrices = receiveOrderDetails.foodPrices as ArrayList<String>
             totalPrice = receiveOrderDetails.totalPrice

             setUserDetails()
             setAdapter()
         }
    }

    private fun setAdapter() {
        binding.foodRecycler.layoutManager = LinearLayoutManager(this)
        val adapter = orderDetailsAdapter(this,foodNames,foodImages,foodQuantity,foodPrices)
        binding.foodRecycler.adapter = adapter
    }

    private fun setUserDetails() {
        binding.name.text = username
        binding.address.text = address
        binding.phone.text = phoneNumber
        binding.totalAmount.text = totalPrice
    }
}