package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView

class favourites_l : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites_l)
        supportActionBar?.hide()

        val listitems = Quotes.QuotesArray as ArrayList<Quotes>
        val arr = listitems
        val list = findViewById<ListView>(R.id.listview)

        list.setOnItemClickListener { parent, view, position, id ->
            val text_fav: String = arr[position].text.toString()
            val author_fav: String = arr[position].author.toString()

            findViewById<TextView>(R.id.fav_text).text = text_fav
            findViewById<TextView>(R.id.fav_author).text = author_fav



        }





    }
}