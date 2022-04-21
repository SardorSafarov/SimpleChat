package com.example.simplechat.topMenu.settings

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.simplechat.R
import com.example.simplechat.databinding.ActivitySettingsBinding
import com.example.simplechat.login.singIn.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private lateinit var alertDialog:AlertDialog.Builder
    private lateinit var binding:ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        alertDialog = AlertDialog.Builder(this)
        window.statusBarColor = Color.WHITE
        logoutBtn()
    }

    private fun logoutBtn() {
        alertDialog.setTitle(R.string.dialog_logout)
        alertDialog.setPositiveButton(R.string.yes){ dialogInterface: DialogInterface, i: Int ->
            auth.signOut()
            startActivity(Intent(this,SignInActivity::class.java))
            finishAffinity()
        }
        alertDialog.setNegativeButton(R.string.no){ dialogInterface: DialogInterface, i: Int -> }
        binding.logoutBtn.setOnClickListener {
            alertDialog.show()
        }
    }
}