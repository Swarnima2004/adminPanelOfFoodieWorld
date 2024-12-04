package com.example.adminpanelfoodieworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfoodieworld.databinding.ActivityProfileBinding
import com.example.adminpanelfoodieworld.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class profile : AppCompatActivity() {
    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var adminRefer: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //initialisation
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        adminRefer = database.reference.child("user")



        binding.backButtom.setOnClickListener {
            finish()
        }

        binding.savebtn.setOnClickListener {
         updateUserData()
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

            if (isEnable) {
                binding.Name.requestFocus()

            }


        }
        retrieveUserData()
    }

    private fun updateUserData() {
        val updateName = binding.Name.text.toString()
        val updateEmail = binding.email.text.toString()
        val updatePassword = binding.password.text.toString()
        val updatePhone = binding.number.text.toString()
        val updateAddress = binding.address.text.toString()
        val currentUser = auth.currentUser?.uid
        if(currentUser != null){

            val userReference = adminRefer.child(currentUser)
            userReference.child("name").setValue(updateName)
            userReference.child("email").setValue(updateEmail)
            userReference.child("password").setValue(updatePassword)
            userReference.child("phone").setValue(updatePhone)
            userReference.child("address").setValue(updateAddress)

            Toast.makeText(this, "Profile Successfully Updated", Toast.LENGTH_SHORT).show()
            //update the user for email and password firebase authentication

            auth.currentUser?.verifyBeforeUpdateEmail(updateEmail)
            auth.currentUser?.updatePassword(updatePassword)
        }
        else {
            Toast.makeText(this, "Profile Update Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun retrieveUserData() {
        val currentUserUid = auth.currentUser?.uid
        if (currentUserUid != null) {
            val userReference = adminRefer.child(currentUserUid)

            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        binding.apply {
                            var ownerName = snapshot.child("name").getValue()
                            var email = snapshot.child("email").getValue()
                            var password = snapshot.child("password").getValue()
                            var address = snapshot.child("address").getValue()
                            var phone = snapshot.child("phone").getValue()
                            setDataToTextView(ownerName, email, password, address, phone)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }

    }

    private fun setDataToTextView(
        ownerName: Any?,
        email: Any?,
        password: Any?,
        address: Any?,
        phone: Any?
    ) {
        binding.Name.setText(ownerName.toString())
        binding.email.setText(email.toString())
        binding.password.setText(password.toString())
        binding.number.setText(phone.toString())
        binding.address.setText(address.toString())
    }
}