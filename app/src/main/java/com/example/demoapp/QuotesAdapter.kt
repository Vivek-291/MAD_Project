package com.example.demoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView



class QuotesAdapter(private var context: Context, var items:ArrayList<Quotes>,var activity : String): BaseAdapter() {
    val a = this.activity
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View  {

        if(a.equals("com.example.demoapp.MainActivity")){
            var view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            val text: TextView = view.findViewById(R.id.list_text)
            val author: TextView =view.findViewById(R.id.list_author)
            text.text=items[position].text
            author.text=items[position].author
            return view
        }

        else{
            val viewd = LayoutInflater.from(context).inflate(R.layout.list_item_dark, parent, false)
            val textd: TextView = viewd.findViewById(R.id.list_text_d)
            val authord: TextView =viewd.findViewById(R.id.list_author_d)
            textd.text=items[position].text
            authord.text=items[position].author

            return viewd
        }



    }
}