package com.example.demoapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.ListView
import com.google.android.material.textfield.TextInputEditText


class details_l : AppCompatActivity()
{
    val a = this::class.java.name
    val listitems = Quotes.QuotesArray as ArrayList<Quotes>
    val adapter = QuotesAdapter(this, listitems,a)
    lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_l)
        supportActionBar?.hide()

        val textCv=intent.getStringExtra("text")
        val authorCv=intent.getStringExtra("author")


        findViewById<TextView>(R.id.quotecard_l).text=textCv
        findViewById<TextView>(R.id.authorcard_l).text=authorCv



        /////////////////////////////////////////////////////////
        val btn = findViewById<ImageView>(R.id.return_back)
        btn.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {startActivity(this)
            }
        }

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setSelectedItemId(R.id.constraint)
        bottomNavigationView.setOnItemSelectedListener() {
            when (it.itemId) {
                R.id.share_menu -> {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.putExtra(Intent.EXTRA_TEXT, textCv)
                    shareIntent.type = "text/plain"
                    startActivity(Intent.createChooser(shareIntent, "send to"))

                    return@setOnItemSelectedListener true
                }

                R.id.copy_menu -> {

                    var myClipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    var myClip: ClipData = ClipData.newPlainText("note_copy", textCv)
                    myClipboard.setPrimaryClip(myClip)

                    Toast.makeText(applicationContext, "Text copied", Toast.LENGTH_SHORT)
                        .show()

                    return@setOnItemSelectedListener true
                }

                else -> {
                    
                    Toast.makeText(applicationContext, "Working on it", Toast.LENGTH_SHORT)
                        .show()
                    val text_fav = findViewById<TextView>(R.id.quotecard_l).text
                    val author_fav = findViewById<TextView>(R.id.authorcard_l).text
                    listitems.add(Quotes(text_fav as String, author_fav as String))
                    list.adapter = adapter

                    return@setOnItemSelectedListener true
                 }
            }
        }


    }




}
