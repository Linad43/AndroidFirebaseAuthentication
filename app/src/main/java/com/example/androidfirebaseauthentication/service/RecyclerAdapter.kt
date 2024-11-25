package com.example.androidfirebaseauthentication.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfirebaseauthentication.R
import com.example.androidfirebaseauthentication.modal.User

class RecyclerAdapter(
    val users: MutableList<User>,
) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val nameUserTV = itemView.findViewById<TextView>(R.id.nameUserTV)
        val numPhoneTV = itemView.findViewById<TextView>(R.id.numPhoneTV)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val user = users[position]
        holder.nameUserTV.text = user.name
        holder.numPhoneTV.text = user.numPhone
    }
}