package com.example.post

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.post.Class.Post
import com.example.post.Entity.PostEntity
import com.example.post.ViewModel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post.*
import java.io.StringReader

class PostFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    var RequestCode = 1

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
        adapter = com.example.post.Adapter.ListAdapter(activity, list)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        if (requestCode == RequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val postdata = data.getStringArrayListExtra(PostDetalleActivity.EXTRA_REPLY)
                val post = PostEntity(postdata[0].toInt(), postdata[1].toInt(), postdata[2],postdata[3])
                postViewModel.insert(post)
            }
        } else {
            Toast.makeText(
                    activity,
                    "Complete todos los campos",
                    Toast.LENGTH_LONG
            ).show()
        }
    }



}