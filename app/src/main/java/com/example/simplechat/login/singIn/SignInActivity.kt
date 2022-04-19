package com.example.simplechat.login.singIn

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplechat.databinding.ActivitySignInBinding
import com.example.simplechat.login.signUp.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        signUp()
    }

    private fun signUp() {
        binding.signUpBtn.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}