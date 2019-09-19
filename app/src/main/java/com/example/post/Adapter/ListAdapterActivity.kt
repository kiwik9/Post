package com.example.post.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.post.Class.Comment
import com.example.post.Class.Post
import com.example.post.DetalleActivity
import com.example.post.R


class ListAdapterActivity(private var activity: Activity?, private var items: ArrayList<Comment>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var title: TextView? = null
        var body: TextView? = null
        init {
            this.title = row?.findViewById<TextView>(R.id.titulo)
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_list, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        view.setOnClickListener { view ->
            activity?.let{
                val intent = Intent(activity, DetalleActivity::class.java)
                intent.putExtra("user_id",items[position].userId)
                it.startActivity(intent)
            }
        }
        var post = items[position]
        viewHolder.title?.text = post.title
        viewHolder.body?.text = post.body

        return view
    }
    override fun getItem(i: Int): Comment {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}