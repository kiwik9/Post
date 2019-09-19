package com.example.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.post.Adapter.ListAdapter
import com.example.post.Model.Post
import kotlinx.android.synthetic.main.fragment_post.*
import java.io.StringReader

class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getPost()
        return inflater!!.inflate(R.layout.fragment_post, container, false)
    }


    private fun setList(list:ArrayList<Post>){
        var adapter : ListAdapter?
        adapter = ListAdapter(activity, list)
        post_list.adapter = adapter

    }

    private fun getPost(){

        val queue = Volley.newRequestQueue(activity)
        val postArray = ArrayList<Post>()
        val url = "https://jsonplaceholder.typicode.com/posts"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val klaxon = Klaxon()
                JsonReader(StringReader(response)).use{
                        reader -> reader.beginArray {
                    while (reader.hasNext()){
                        val post = klaxon.parse<Post>(reader)
                        postArray.add(post!!)
                    }

                }
                }
                setList(postArray)
            },
            Response.ErrorListener { Toast.makeText(activity, "MAl", Toast.LENGTH_SHORT).show()})
        queue.add(stringRequest)

    }
}