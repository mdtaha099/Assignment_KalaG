package com.example.myapplication.adapters

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DetailsActivity
import com.example.myapplication.PostFragment
import com.example.myapplication.R
import com.example.myapplication.model.postsListItem



class PostsAdapter(var list: List<postsListItem>, private val clickListener: (postsListItem) -> Unit) :
    RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {




    class MyViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.username_post)
        val post_body = v.findViewById<TextView>(R.id.user_post)
        val Id = v.findViewById<TextView>(R.id.id)
        val userId = v.findViewById<TextView>(R.id.userid)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.posts_card, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item  = list[position]

        holder.name.text = item.title
        holder.post_body.text = item.body
        holder.Id.text = item.id.toString()
        holder.userId.text = item.userId.toString()

        holder.itemView.setOnClickListener { clickListener(item) }
    }

}