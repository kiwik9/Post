package com.example.post

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.post.Adapter.ListAdapterActivity
import com.example.post.Class.Comment
import com.example.post.Class.Post
import com.example.post.Entity.CommentEntity
import com.example.post.ViewModel.CommentViewModel
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.fragment_post.*

class DetalleActivity : AppCompatActivity() {

    private val commentRequestCode = 1
    private lateinit var commentViewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)



        fab.setOnClickListener {
            val intent = Intent(this, ComentarioActivity::class.java)
            startActivityForResult(intent, commentRequestCode)
        }



    }

    fun setAllData(){

        var adapter : ListAdapterActivity?
        var list : ArrayList<Comment>? = null

        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel::class.java)

        commentViewModel.allPost.observe(this, Observer { comments ->
            comments?.let {
                for (item in it){

                }
            }
        })
        adapter = ListAdapterActivity(this, list!!)
        post_list.adapter = adapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == commentRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val commentdata = data.getStringArrayListExtra(ComentarioActivity.EXTRA_REPLY)
                val comment = CommentEntity(1, 1 , commentdata[0],commentdata[1],commentdata[2])
                commentViewModel.insert(comment)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Complete todos los campos",
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}

