package com.example.kotlinapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bett.kotlinrecyclerview.adapters.RecyclerAdapter
import com.example.`interface`.ApiInterface
import android.support.v4.content.ContextCompat.startActivity
import com.example.pojo.ItemDescription
import com.example.pojo.ItemDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchActivity : AppCompatActivity() {

    val descriptionInterface by lazy {
        ApiInterface.createItemInterface()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val searchRecycler = findViewById<RecyclerView>(R.id.search_list)
        val items = intent.getParcelableArrayListExtra<ItemDetail>("items")
        val recyclerAdapter = RecyclerAdapter(items)
        searchRecycler.layoutManager = LinearLayoutManager(this)
        searchRecycler.adapter = recyclerAdapter

        recyclerAdapter.onItemClick = { item ->
            getDescription(item.id.toString(), item.thumbnail.toString(), item.price.toString(), item.title.toString())

        }

    }

    private fun getDescription(id: String, thumbnail: String, price: String, title: String) {
        disposable = descriptionInterface.descriptionResult(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(this, result, thumbnail, price, title) },
                { error -> showError(error.message.toString()) }
            )
    }

    private fun showError(toString: String) {
        val error = toString
    }

    private fun showResult(searchActivity: SearchActivity, result: ItemDescription, thumbnail: String, price: String, title: String) {
        goToVipResultActivity(searchActivity, result, thumbnail, price, title)
    }

    private fun goToVipResultActivity(context: Context, model: ItemDescription, thumbnail: String, price: String, title: String) {
        val intent = Intent(context, VipActivity::class.java)
        intent.putExtra("item description", model)
        intent.putExtra("thumbnail", thumbnail)
        intent.putExtra("price", price)
        intent.putExtra("title", title)
        startActivity(context, intent, null)
    }

}
