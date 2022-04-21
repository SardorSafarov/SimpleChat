package com.example.simplechat.login.signUp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplechat.databinding.ActivitySignUpBinding
import com.example.simplechat.entity.UserEntity
import com.example.simplechat.log.D
import com.example.simplechat.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("user")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        signIn()
        createAccount()
    }

    fun createAccount() {
        binding.signUpBtn.setOnClickListener {
            val password1 = binding.passwordEditText1.text.toString().trim()
            val password2 = binding.passwordEditText2.text.toString().trim()
            if (password1 == password2 && password1 !="") {
                binding.apply {
                    auth.createUserWithEmailAndPassword(
                        emailAddressEditText.text.toString(),
                        passwordEditText2.text.toString()
                    ).addOnCompleteListener(this@SignUpActivity) { task ->
                            if (task.isSuccessful) {
                                D.d("createUserWithEmail:success")
                                    database.child(auth.uid.toString()).setValue(
                                    UserEntity(
                                        auth.uid.toString(),
                                        nameEditText.text.toString(),
                                        emailAddressEditText.text.toString(),
                                        "sardor"
                                    )
                                )
                                startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                                finishAffinity()
                            } else {
                                D.d("createUserWithEmail:failure")
                                Toast.makeText(
                                    baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }

            } else
                Toast.makeText(this, "Please check your password!!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun signIn() {
        binding.signInBtn.setOnClickListener {
            finish()
        }
    }
}