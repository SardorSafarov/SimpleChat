package com.example.simplechat.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplechat.R
import com.example.simplechat.adapter.users.UsersAdapter
import com.example.simplechat.databinding.ActivityMainBinding
import com.example.simplechat.entity.UserEntity
import com.example.simplechat.firebase.viewModel.login.LoginViewModel
import com.example.simplechat.topMenu.profil.ProfilActivity
import com.example.simplechat.topMenu.settings.SettingsActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userChatViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }
    private val adapter: UsersAdapter by lazy { UsersAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        topMenu()
        recycListUser()

    }

    private fun recycListUser() {
        binding.userList.adapter = adapter
        binding.userList.layoutManager = LinearLayoutManager(this)
        userChatViewModel.readChatUser()
        userChatViewModel.user.observe(this, Observer {
            adapter.getData(it as MutableList<UserEntity>)
        })
    }


    private fun topMenu() {
        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.top_menu_btn -> {
                    showPopup(view)
                }
            }
        }
        binding.topMenuBtn.setOnClickListener(clickListener)

    }


    @SuppressLint("RestrictedApi")
    private fun showPopup(view: View) {

        val menuBuilder = MenuBuilder(this)
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.top_menu, menuBuilder)
        val optionsMenu = MenuPopupHelper(this, menuBuilder, view)
        optionsMenu.setForceShowIcon(true)
        menuBuilder.setCallback(object : MenuBuilder.Callback {
            override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
                when(item.itemId)
                {
                    R.id.profil ->{
                        startActivity(Intent(this@MainActivity,ProfilActivity::class.java))
                        true
                    }
                    R.id.settings ->{
                        startActivity(Intent(this@MainActivity,SettingsActivity::class.java))
                        true
                    }
                    R.id.share ->
                    {
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT,
                            "Ishlab chiqaruvchi Sardor Safarov \nMurojat uchun tel:+99899 505 66 98");
                        intent.type = "text/plain"
                        startActivity(intent)
                    }
                    else -> false
                }
                return true
            }

            override fun onMenuModeChange(menu: MenuBuilder) {}
        })
        optionsMenu.show()

    }

}