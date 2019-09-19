package com.example.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.post.Class.Post
import com.example.post.Entity.PostEntity
import com.example.post.ViewModel.CommentViewModel
import com.example.post.ViewModel.PostViewModel
import kotlinx.android.synthetic.main.fragment_guardado2.*
import kotlinx.android.synthetic.main.fragment_post.*

class GuardadoFragment : Fragment() {

    var list : List<PostEntity>? = null

    private lateinit var posttViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getList()
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_guardado2, container, false)
    }

    private fun getList(){
        var adapter : ListAdapter?
        var list: ArrayList<Post>? = null
        posttViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        posttViewModel.allPost.observe(this, Observer { post ->
            post?.let {
                for (item in it){
                    var po = Post(item.userid,item.id,item.title,item.body)
                    list?.add(po)
                }
            }
        })
        if(list != null){
        adapter = com.example.post.Adapter.ListAdapter(activity, list)
        save_list.adapter = adapter
            }
        else{
            Toast.makeText(
                activity,
                "No hay datos",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun setList(){

}
}