package com.kronos.quota

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.kronos.View.Data
import com.kronos.View.Factory
import com.kronos.View.ViewModel
import com.kronos.quota.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModelList: ViewModel
    lateinit var textIt: TextView
    lateinit var author: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textIt = binding.QuoteList
        author = binding.AuthorList

        viewModelList = ViewModelProvider(this, Factory(this)).get(ViewModel::class.java)
        setQuote(viewModelList.getQuote())
    }
    fun  setQuote(quote: Data) {
        textIt.text = quote.text
        author.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("*/*")
        intent.putExtra(Intent.EXTRA_TEXT, viewModelList.getQuote().text)
        startActivity(intent)
    }
    fun onNext(view: View) {
        setQuote(viewModelList.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote(viewModelList.previousQuote())
    }

}