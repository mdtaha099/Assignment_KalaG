package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CommentAdapter
import com.example.myapplication.adapters.PostsAdapter
import com.example.myapplication.model.CommentItem
import com.example.myapplication.model.postsListItem
import com.example.myapplication.retrofit.PostsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {

    private lateinit var titleTv: TextView
    private lateinit var post_bodyTv: TextView
    private lateinit var saveBtn: ImageView


    private lateinit var recyclerViewComments: RecyclerView
    private lateinit var commentAdapter: CommentAdapter

    private var k = 1







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        titleTv = findViewById(R.id.title_tv)
        post_bodyTv = findViewById(R.id.post_tv)
        saveBtn = findViewById(R.id.save_btn)


        val id = intent.getIntExtra("id", 1)
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")

        titleTv.text = title
        post_bodyTv.text = body

        recyclerViewComments = findViewById(R.id.recycler_view_comments)
        commentAdapter = CommentAdapter()
        recyclerViewComments.layoutManager = LinearLayoutManager(this)

        recyclerViewComments.adapter = commentAdapter

        fetchCommentsFromApi(id)




        saveBtn.setOnClickListener(View.OnClickListener {
            addToFavs()
        })



    }

    private fun fetchCommentsFromApi(id: Int) {
        var retrofit = Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsAPI::class.java)

        var call = retrofit.getComments(id)

        call.enqueue(object : Callback<List<CommentItem>> {
            override fun onResponse(call: Call<List<CommentItem>>, response: Response<List<CommentItem>>) {
                if (response.isSuccessful) {
                    val comm = response.body()
                    commentAdapter.setData(comm!!)

                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<List<CommentItem>>, t: Throwable) {
                // Handle network or other errors
            }
        })
    }



//    private fun fetchCommentsFromApi(val id) {

//        val retrofit = Retrofit.Builder()
//            .baseUrl(Base_Url)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val apiService = retrofit.create(PostsAPI::class.java)
//        val call = apiService.getComments()
//
//        call.enqueue(object : Callback<List<CommentItem>> {
//            override fun onResponse(call: Call<List<CommentItem>>, response: Response<List<CommentItem>>) {
//                if (response.isSuccessful) {
//                    val comments = response.body()
//                    if (comments != null) {
//                        commentAdapter.setData(comments)
//                    }
//                } else {
//                    // Handle the error
//                }
//            }
//
//            override fun onFailure(call: Call<List<CommentItem>>, t: Throwable) {
//                // Handle the failure
//            }
//        })


//        var retrofit = Retrofit.Builder()
//            .baseUrl(Base_Url)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(PostsAPI::class.java)
//
//        var call = retrofit.getComments(id)
//
//        call.enqueue(object : Callback<List<CommentItem>> {
//            override fun onResponse(call: Call<List<CommentItem>>, response: Response<List<CommentItem>>) {
//                if (response.isSuccessful) {
//                    val comm = response.body()
//                    commentAdapter.setData(comm!!)
//
//                } else {
//                    // Handle API error
//                }
//            }
//
//            override fun onFailure(call: Call<List<CommentItem>>, t: Throwable) {
//                // Handle network or other errors
//            }
//        })}




    private fun addToFavs() {

        if(k == 1){
            saveBtn.setImageResource(R.drawable.fav_clicked)
            k = 0
        }
        else {
            saveBtn.setImageResource(R.drawable.fav_unclicked)
            k = 1
        }

    }
}