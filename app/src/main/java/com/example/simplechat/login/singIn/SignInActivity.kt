package com.example.simplechat.login.singIn

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplechat.databinding.ActivitySignInBinding
import com.example.simplechat.login.signUp.SignUpActivity
import com.example.simplechat.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        signUp()
        singIn()
    }

    private fun singIn() {
        binding.signInBtn.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.emailAddressEditText.text.toString(),binding.passwordEditText.text.toString())
                .addOnCompleteListener(this) { task->
                    if(task.isSuccessful)
                    {
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this, "Username or Passwor error!!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun signUp() {
        binding.signUpBtn.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}