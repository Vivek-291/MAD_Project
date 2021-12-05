package com.example.demoapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.demoapp.R.id.constraint
import com.google.android.material.bottomnavigation.BottomNavigationView

class details_d : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_d)
        supportActionBar?.hide()
        val btn = findViewById<ImageView>(R.id.return_back)
        btn.setOnClickListener {
            Intent(this, DarkActivity::class.java).apply {startActivity(this)
            }
        }


        val textCv=intent.getStringExtra("text")
        val authorCv=intent.getStringExtra("author")
        findViewById<TextView>(R.id.quotecard_d).text=textCv
        findViewById<TextView>(R.id.authorcard_d).text=authorCv


        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setSelectedItemId(constraint)
        bottomNavigationView.setOnItemSelectedListener() {
            when (it.itemId)
            {
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


                    return@setOnItemSelectedListener true
                }
            }
        }

    }
}