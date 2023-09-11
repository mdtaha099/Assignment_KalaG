package com.example.myapplication.retrofit

import com.example.myapplication.model.CommentItem
import com.example.myapplication.model.postsListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsAPI {

    @GET("/posts")
    fun getPosts() : Call<List<postsListItem>>

    @GET("/comments")
    fun getComments(@Query("postId") postId: Int) : Call<List<CommentItem>>

}