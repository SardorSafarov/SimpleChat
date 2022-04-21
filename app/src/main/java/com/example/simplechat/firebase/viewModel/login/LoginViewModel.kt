package com.example.simplechat.firebase.viewModel.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.simplechat.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel:ViewModel() {

    private val firebase =  FirebaseDatabase.getInstance().getReference("USERS")
//    fun insertChatUser(userEntity: UserEntity) {
//        firebase.child(firebase.push().key.toString()).
//        setValue(userEntity)
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Log.d("sardor", "qo`shildi")
//                } else {
//                    Log.d("sardor", "nimadir bo`ldiii")
//                }
//            }
//    }

}