package com.example.simplechat.firebase.viewModel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplechat.entity.UserEntity
import com.example.simplechat.log.D
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginViewModel:ViewModel() {

    private val firebase =  FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val _user = MutableLiveData<List<UserEntity>>()
    val user: LiveData<List<UserEntity>>
        get() = _user
    fun readChatUser()
    {
        firebase.reference.child("user")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    val items = mutableListOf<UserEntity>()
                    p0.children.forEach {
                        D.d(it.toString())
                        val item = it.getValue(UserEntity::class.java)
                        if(auth.uid.toString() != it.key!!){
                        item?._id = it.key!!
                        item.let {
                            if (it != null) {
                                items.add(it)
                            }
                        }
                        }
                    }
                    _user.value =items
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

}