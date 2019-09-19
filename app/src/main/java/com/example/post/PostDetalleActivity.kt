package com.example.post

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.example.post.Class.Post
import com.example.post.Entity.PostEntity
import com.example.post.ViewModel.PostViewModel
import kotlinx.android.synthetic.main.activity_post_detalle.*

class PostDetalleActivity : AppCompatActivity() {

    var RequestCode = 1

    private lateinit var postViewModel: PostViewModel
    var postEntity: PostEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detalle)
        getData()

        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)


        btnguardarPost.setOnClickListener {
            if(postEntity!= null){
            postViewModel.insert(postEntity!!)
            }
        }
    }

    fun getData(){

        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts/"
        var id = intent.getIntExtra("user_id",0)
        val stringRequest = StringRequest(
            Request.Method.GET, url+id,
            Response.Listener<String> { response ->

                var post = Klaxon().parse<Post>(response)
                lblUserId.text = post?.userId.toString()
                //post?.id.toString()
                lblTitulo.text = post?.title
                lblBody.text = post?.body
                postEntity= PostEntity(post?.userId!!,post?.id!!,post?.title!!.toString(),post?.body!!.toString())

            },
            Response.ErrorListener { Toast.makeText(this, "Ocurrio algo mal, vuelva a intentarlo", Toast.LENGTH_SHORT).show()})
        queue.add(stringRequest)
    }


}
