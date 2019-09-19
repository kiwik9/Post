package com.example.post.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.post.DetalleActivity
import com.example.post.Model.Post
import com.example.post.R

class ListAdapter(private var activity: FragmentActivity?, private var items: ArrayList<Post>) :  BaseAdapter(){
    private class ViewHolder(row: View?) {
        var title: TextView? = null
        var body: TextView? = null
        init {
            this.title = row?.findViewById<TextView>(R.id.titulo)
            this.body = row?.findViewById<TextView>(R.id.descripcion)
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
            val intent = Intent(activity, DetalleActivity::class.java)
            intent.putExtra("id",items[position].id)
            activity?.startActivity(intent)
        }
        var post = items[position]
        viewHolder.title?.text = post.title
        viewHolder.body?.text = post.body

        return view
    }
    override fun getItem(i: Int): Post {
        return items[i]
    }
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
    override fun getCount(): Int {
        return items.size
    }
}