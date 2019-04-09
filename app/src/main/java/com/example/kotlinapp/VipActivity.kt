package com.example.kotlinapp

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import android.widget.TextView
import com.example.pojo.ItemDescription
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_vip.*

class VipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip)
        val itemThumbnail = findViewById<ImageView>(R.id.vip_thumbnail)
        val itemTitle = findViewById<TextView>(R.id.vip_title)
        val itemPrice = findViewById<TextView>(R.id.vip_price)
        val itemDescription = findViewById<TextView>(R.id.vip_description)

        val itemDesc = intent.getParcelableExtra("item description") as ItemDescription
        val title = intent.getStringExtra("title") as String
        val price = intent.getStringExtra("price") as String
        val thumbnail = intent.getStringExtra("thumbnail") as String

        Picasso.with(this).load(thumbnail).into(itemThumbnail)
        itemTitle.text = title
        itemPrice.text = price
        if(itemDesc.plainText != null){
            val htmlAsSpanned = itemDesc.plainText!!.toSpanned()
            itemDescription.text = htmlAsSpanned
        }

    }

    fun String.toSpanned(): Spanned {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            return Html.fromHtml(this)
        }
    }

}
