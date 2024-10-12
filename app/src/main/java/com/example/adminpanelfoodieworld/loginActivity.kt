package com.example.adminpanelfoodieworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivityLoginBinding
import com.example.adminpanelfoodieworld.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class loginActivity : AppCompatActivity() {
     private lateinit var email : String
     private lateinit var password : String
     private lateinit var auth : FirebaseAuth
     private lateinit var database : DatabaseReference


    private val binding: ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
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

        auth = Firebase.auth
        database = Firebase.database.reference

        binding.logbtn.setOnClickListener {
            //getting the text from the edittext
            email = binding.email.text.toString().trim()
            password = binding.Password.text.toString().trim()

            if(email.isBlank() || password.isBlank()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                createUserAccount(email, password)

            }

        }

        binding.createAccount.setOnClickListener{
            val intent = Intent(this,signinActivity::class.java)
            startActivity(intent)


    }
}

    private fun createUserAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                val user: FirebaseUser? = auth.currentUser
                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
                updateUi(user)
            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        saveUserData()
                        updateUi(user)
                        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Authentication failed",Toast.LENGTH_SHORT).show()
                        Log.d("Account","createAccount: Authentication failed",task.exception)
                    }
                }
            }
        }
    }

    private fun saveUserData() {
        email = binding.email.text.toString().trim()
        password = binding.Password.text.toString().trim()

        val user = UserModel(email,password)
        val userId : String? = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let{
            database.child("user").child(it).setValue(user)
        }
    }

    private fun updateUi(user: FirebaseUser?) {
       startActivity(Intent(this,MainActivity::class.java))
    }
}