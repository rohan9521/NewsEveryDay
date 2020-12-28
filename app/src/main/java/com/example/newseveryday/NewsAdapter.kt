package com.example.newseveryday

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(): RecyclerView.Adapter<NewsViewHolder>() {
    var items:MutableList<NewsObject> = mutableListOf()
    var context: Context? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener(){
            Toast.makeText(parent.context,"${items[viewHolder.adapterPosition]}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem  = items[position]
        holder.titleView.text = currentItem.title.toString()
    //    holder.descriptionView.text = currentItem.description.toString()
        holder.published_atView.text = currentItem.publishedAt.toString()
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.imageView)
    }
}

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView = itemView.findViewById<TextView>(R.id.text_title)
    val descriptionView = itemView.findViewById<TextView>(R.id.text_description)
    val published_atView = itemView.findViewById<TextView>(R.id.text_published_at)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView)
}