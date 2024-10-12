package com.example.adminpanelfoodieworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivitySigninBinding
import com.example.adminpanelfoodieworld.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class signinActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String
    private lateinit var nameOfRestaurent: String
    private lateinit var database: DatabaseReference

    private val binding: ActivitySigninBinding by lazy {
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

        val locationList = arrayOf("Jaipur", "Delhi", "Gorakhpur", "Agar")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)
        val autoCompleteTextView = binding.locationList
        autoCompleteTextView.setAdapter(adapter)

        //firebase auth initialize 
        auth = Firebase.auth

        //firebase database initialize
        database = Firebase.database.reference



        binding.signinbtn.setOnClickListener {
            //getting the text from the edit text
            email = binding.emailAddress.text.toString().trim()
            nameOfRestaurent = binding.restaurentName.text.toString().trim()
            username = binding.ownerName.text.toString().trim()
            password = binding.Password.text.toString().trim()

            if (username.isBlank() || nameOfRestaurent.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }


        }
        binding.alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, loginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Account failed to create", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure", task.exception)
            }


        }
    }

    //saving data in database
    private fun saveUserData() {
        username = binding.ownerName.text.toString().trim()
        nameOfRestaurent = binding.restaurentName.text.toString().trim()
        email = binding.emailAddress.text.toString().trim()
        password = binding.Password.text.toString().trim()

        val user = UserModel(username, nameOfRestaurent, email, password)
        val userId: String = FirebaseAuth.getInstance().currentUser!!.uid
        //save the data of the user
        database.child("user").child(userId).setValue(user)
    }
}