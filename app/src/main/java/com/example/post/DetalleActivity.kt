package com.example.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.example.post.Database.AppDB
import com.example.post.Entity.ComentarioEntity
import com.example.post.Entity.PostEntity
import com.example.post.Model.Post
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetalleActivity : AppCompatActivity(), View.OnClickListener {

    private var postDetalle : PostEntity? = null
    private var complete : Boolean? = false
    private var isSame : Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        getData()

        btnguardar.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnguardar -> {
                isSave(postDetalle?.id!!)
            }
            R.id.btneliminar -> {
                isSave(postDetalle?.id!!)
            }
        }
    }

    fun isSave(id: Int) : PostEntity{
        var  data : PostEntity? = null
        if(complete!!){

        val db = AppDB(this)
        GlobalScope.launch {
             data = db.PostDao().findById(id)

        }
        }
        return data!!
    }

    fun savepost(){
        if(complete!! && isSame!!){
        val db = AppDB(this)
        GlobalScope.launch {
                db.PostDao().insertPost(postDetalle!!)
        }
        }
    }

    fun getData(){

        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts/"
        var id = intent.getIntExtra("id",0)
        val stringRequest = StringRequest(
            Request.Method.GET, url+id,
            Response.Listener<String> { response ->

                var post = Klaxon().parse<Post>(response)
                text_userid.text = post?.userId.toString()
                text_id.text = post?.id.toString()
                text_title.text = post?.title
                text_body.text = post?.body
                postDetalle = PostEntity(post?.userId!!,post?.id!!,post?.title!!,post?.body!!)
                complete = true
                if(postDetalle?.equals(isSave(post?.id!!))!!)
                    isSame = true

            },
            Response.ErrorListener { Toast.makeText(this, "Mal", Toast.LENGTH_SHORT).show()})
        queue.add(stringRequest)
    }


}
