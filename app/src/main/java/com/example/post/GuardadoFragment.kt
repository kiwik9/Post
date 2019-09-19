package com.example.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.post.Adapter.ListAdapter
import com.example.post.Database.AppDB
import com.example.post.Entity.PostEntity
import com.example.post.Model.Post
import kotlinx.android.synthetic.main.fragment_guardado.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GuardadoFragment : Fragment() {

    var list : List<PostEntity>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setList()
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_guardado, container, false)
    }

    private fun getList(){

        val db = activity?.let { AppDB(it) }
        GlobalScope.launch {
             list = db?.PostDao()?.getAll()!!
        }

    }

    private fun setList(){
        getList()
        var list : ArrayList<Post>? = null
        for(item in list!!){
            list?.add(Post(item.userId,item.id,item.title,item.body))
        }
        var adapter : ListAdapter?
        adapter = ListAdapter(activity, list!!)
        save_list.adapter = adapter
    }
}