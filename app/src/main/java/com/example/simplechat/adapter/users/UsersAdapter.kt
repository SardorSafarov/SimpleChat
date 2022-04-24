package com.example.simplechat.adapter.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplechat.R
import com.example.simplechat.databinding.ItemUserChatBinding
import com.example.simplechat.entity.UserEntity


class UsersAdapter(private val listener:userOnclickListener) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    interface userOnclickListener{
        fun setOnClickListener(_id: String)
    }

    var list :MutableList<UserEntity> = mutableListOf()

     inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bind = ItemUserChatBinding.bind(view)
        fun bind(userEntity: UserEntity)
        {
            bind.userName.text = userEntity.name
            itemView.setOnClickListener {
                listener.setOnClickListener(userEntity._id)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user_chat, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun getData(list: MutableList<UserEntity>)
    {
        this.list = list
        notifyDataSetChanged()
    }
}