package com.example.simplechat.splashScreen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.simplechat.databinding.ActivitySplashScreenBinding
import com.example.simplechat.login.singIn.SignInActivity
import com.example.simplechat.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        val currentUser = auth.currentUser
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if(currentUser!=null)
                {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else
                {
                    startActivity(Intent(this,SignInActivity::class.java))
                    finish()
                }
            },
            2000 // value in milliseconds
        )

    }
}