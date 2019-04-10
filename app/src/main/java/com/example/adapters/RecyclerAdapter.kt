package com.bett.kotlinrecyclerview.adapters

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlinapp.R
import com.example.pojo.ItemDetail
import com.squareup.picasso.Picasso


/**
 */

class RecyclerAdapter(private var items: ArrayList<ItemDetail>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val searchDto = items[position]
        p0.titleSearch?.text = searchDto.title
        p0.priceSearch?.text = "$ ${searchDto.price.toString()}"
        Picasso.with(p0.itemView.context).load(searchDto.thumbnail).into(p0.searchImage);
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0?.context)
            .inflate(R.layout.search_results, p0, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var titleSearch: TextView? = null
        var priceSearch: TextView? = null

        var searchImage: ImageView? = null

        init {
            this.titleSearch = row.findViewById(R.id.item_title)
            this.priceSearch = row.findViewById(R.id.item_price)
            this.searchImage = row.findViewById(R.id.item_thumbnail)
        }
    }
}