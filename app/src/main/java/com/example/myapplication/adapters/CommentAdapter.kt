package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CommentItem
import com.example.myapplication.model.postsListItem

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {
    private var comments: List<CommentItem> = emptyList()

    fun setData(comments: List<CommentItem>) {
        this.comments = comments
        notifyDataSetChanged()
    }


    class MyViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.name_email_comments)
        val comment_body = v.findViewById<TextView>(R.id.comment_body)





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.comments_card, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item  = comments[position]

        holder.name.text = item.name +"\n(${item.email})"
        holder.comment_body.text = item.body

    }


}