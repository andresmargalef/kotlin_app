package com.example.kotlinapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Button
import android.widget.EditText
import com.example.`interface`.SearchInterface
import com.example.pojo.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val searchInterface by lazy {
        SearchInterface.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchText = findViewById<EditText>(R.id.search)
        val buttonSearch = findViewById<Button>(R.id.search_button)

        buttonSearch.setOnClickListener { view ->
            beginSearch(searchText.text.toString())
        }
    }

    private fun beginSearch(srsearch: String) {
        disposable =
            searchInterface.searchResult(srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> showResult(this, result) },
                    { error -> showError(error.message.toString()) }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    fun showResult(context: Context, model: Results) {
        goToSearchResultActivity(context, model)
    }

    fun showError(s: String) {
        val error = s
    }

    fun goToSearchResultActivity(context: Context, model: Results) {
        val intent = Intent(context, SearchActivity::class.java)
        intent.putParcelableArrayListExtra("items", model.results)
        startActivity(context, intent, null)
    }

}
