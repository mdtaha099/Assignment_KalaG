package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.PostsAdapter
import com.example.myapplication.model.postsListItem
import com.example.myapplication.retrofit.PostsAPI
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



var Base_Url = "https://jsonplaceholder.typicode.com"


class PostFragment : Fragment() {

    private lateinit var postsAdapter : PostsAdapter
    private lateinit var recyclerView: RecyclerView


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_post, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_posts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        postsAdapter = PostsAdapter(emptyList()) { post ->
            // Handle item click here, e.g., navigate to another fragment or activity
        }
        recyclerView.adapter = postsAdapter

        fetchDataFromApi()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        rV_posts = view.findViewById<RecyclerView>(R.id.recycler_view_posts)
//        rV_posts.layoutManager = LinearLayoutManager(activity)

//        val adapter = PostsAdapter(this, emptyList()) { post ->
//            // Handle item click (e.g., navigate to a detail activity)
//
//        }
//        rV_posts.adapter = adapter

//        rV_posts = view.findViewById(R.id.recycler_view_posts)
//        rV_posts.layoutManager = LinearLayoutManager(context)

//        var retrofit = Retrofit.Builder()
//            .baseUrl(Base_Url)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(PostsAPI::class.java)
//
//        var retroData = retrofit.getPosts()
//
//        retroData.enqueue(object : Callback<List<postsListItem>> {
//            override fun onResponse(call: Call<List<postsListItem>>, response: Response<List<postsListItem>>) {
//                if (response.isSuccessful) {
//                    val posts = response.body()
//                    if (posts != null) {
//                        adapter.(posts)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<postsListItem>>, t: Throwable) {
//                // Handle failure (e.g., show an error message)
//            }
//        })
//
//        getAllData()


    }



   private fun getAllData() {


//        retroData.enqueue(object : Callback<List<postsListItem>>{
//            override fun onResponse(
//                call: Call<List<postsListItem>>,
//                response: Response<List<postsListItem>>
//            ) {
//                var data = response.body()!!
//
//                postsAdapter = PostsAdapter(this@PostFragment, data)
//                rV_posts.adapter = postsAdapter
//
//            }
//
//            override fun onFailure(call: Call<List<postsListItem>>, t: Throwable) {
//
//            }
//
//        })



    }


    private fun fetchDataFromApi() {
//        val apiService = RetrofitClient.
//        val call = apiService.getPosts()

        var retrofit = Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsAPI::class.java)

        var call = retrofit.getPosts()

        call.enqueue(object : Callback<List<postsListItem>> {
            override fun onResponse(call: Call<List<postsListItem>>, response: Response<List<postsListItem>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    postsAdapter = PostsAdapter(posts) { post ->
                        // Handle item click here, e.g., navigate to another fragment or activity

                        Toast.makeText(activity, "Hello ${post.title}", Toast.LENGTH_SHORT).show()

                        val intent = Intent(activity, DetailsActivity::class.java)

                        intent.putExtra("id", post.id)
                        intent.putExtra("title", post.title)
                        intent.putExtra("body", post.body)

                        startActivity(intent)
                    }
                    recyclerView.adapter = postsAdapter
                } else {
                    // Handle API error
                }
            }

            override fun onFailure(call: Call<List<postsListItem>>, t: Throwable) {
                // Handle network or other errors
            }
        })
    }





}


