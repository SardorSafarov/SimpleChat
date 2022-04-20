package com.example.simplechat.topMenu.profil

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.simplechat.R
import com.example.simplechat.databinding.ActivityProfilBinding
import com.example.simplechat.databinding.BottomShetBarSelectImageBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        finishFun()
        chageImage()
        upDate()
    }

    private fun upDate() {
        binding.updateBtn.setOnClickListener {
            finish()
        }
    }

    private fun chageImage() {
        val bottomDialog = BottomSheetDialog(this, R.style.BottomSheetDiaolg)
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_shet_bar_select_image, null)
        bottomDialog.setContentView(view)
        val bind = BottomShetBarSelectImageBinding.bind(view)
        binding.userImageBtn.setOnClickListener {
            bottomDialog.show()
        }
    }

    private fun finishFun() {
        binding.finish.setOnClickListener {
            finish()
        }
    }
}