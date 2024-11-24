package com.example.androidfirebaseauthentication.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfirebaseauthentication.R
import com.example.androidfirebaseauthentication.modal.EmailMessage

class RecyclerAdapter(
    val messages: ArrayList<EmailMessage>,
) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val titleTV = itemView.findViewById<TextView>(R.id.titleTV)
        val messageTV = itemView.findViewById<TextView>(R.id.textTV)
        val authorTV = itemView.findViewById<TextView>(R.id.authorTV)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val message = messages[position]
        holder.titleTV.text = message.title
        holder.messageTV.text = message.message
        holder.authorTV.text = message.author
    }
}