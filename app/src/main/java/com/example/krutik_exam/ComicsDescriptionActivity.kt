package com.example.krutik_exam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.io.Serializable

class ComicsDescriptionActivity : AppCompatActivity(){

    private lateinit var comicsImage: ImageView
    private lateinit var comicsTitle: TextView
    private lateinit var comicsPrice: TextView
    private lateinit var comicsDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics_description)

        comicsImage = findViewById(R.id.imageview_poster)
        comicsTitle = findViewById(R.id.textview_title)
        comicsPrice = findViewById(R.id.textview_price)
        comicsDescription = findViewById(R.id.textview_description)

        Glide.with(this)
            .load(this.intent.getStringExtra("thumbnail"))
            .into(comicsImage)
        comicsTitle.text = this.intent.getStringExtra("title")
        comicsPrice.text = "Цена: " + this.intent.getDoubleExtra("price", 0.0).toString() + "$"
        comicsDescription.text = this.intent.getStringExtra("description")
    }
}