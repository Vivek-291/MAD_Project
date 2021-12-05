package com.example.demoapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import org.json.JSONArray
import java.net.URL

class MainActivity : AppCompatActivity()
{

    val a = this::class.java.name
    val listitems = Quotes.QuotesArray as ArrayList<Quotes>
    val adapter = QuotesAdapter(this, listitems,a)
    lateinit var list: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

/////////////////////////DESIGN/////////////////////////////////////////////////////////////////////
        val textView = findViewById<TextView>(R.id.titletxt);
        val typeface = ResourcesCompat.getFont(
            this,
            R.font.gera
        );
        textView.typeface = typeface;

        val btn = findViewById<ImageView>(R.id.toogle)
        btn.setOnClickListener {
            Intent(this, DarkActivity::class.java).apply {
                startActivity(this)
            }
        }

        val btnfav = findViewById<ImageView>(R.id.favourite_btn)
        btnfav.setOnClickListener {
            Intent(this, favourites_l::class.java).apply {
                startActivity(this)
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////
        list = findViewById(R.id.list_view)
        val arr = listitems
        list.setOnItemClickListener { parent, view, position, id ->
            val text: String = arr[position].text
            val author: String = arr[position].author


            val intent = Intent(this, details_l::class.java)
            intent.putExtra("text", text)
            intent.putExtra("author", author)
            startActivity(intent)


        }
        quotes().execute()
    }

    inner class quotes() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("\n" +
                            "\n" +
                            "https://api.json-generator.com/templates/LBY8503hzscX/data?access_token=l9ad8e4mzmd5rbb3ghf5ag1vvzepaxpp7dwbcdyj").readText(
                        Charsets.UTF_8
                    )

            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {

                val jsonarr = JSONArray(result)

                for (i in 0..1000) {

                    val jsonObj_0 = jsonarr.getJSONObject(i)
                    val text_json = jsonObj_0.getString("text")
                    val author_json = "~ " + jsonObj_0.getString("author")

                    listitems.add(Quotes(text_json, author_json))
                    list.adapter = adapter
                }

            } catch (e: Exception) {

            }

        }
    }


}
