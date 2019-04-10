package com.example.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bett.kotlinrecyclerview.adapters.RecyclerAdapter
import com.example.pojo.ItemDetail

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val searchRecycler = findViewById<RecyclerView>(R.id.search_list)
        val items = intent.getParcelableArrayListExtra<ItemDetail>("items")

        searchRecycler.layoutManager = LinearLayoutManager(this)
        searchRecycler.adapter = RecyclerAdapter(items)

    }

}
