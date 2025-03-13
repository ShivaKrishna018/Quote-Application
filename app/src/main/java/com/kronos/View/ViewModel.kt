package com.kronos.View

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class ViewModel(val context: Context): ViewModel() {

    var index: Int = 0
    private var quoteList: Array<Data> = emptyArray()

    init {
        quoteList = loadQuoteList()
    }

    private fun loadQuoteList(): Array<Data> {

        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Data>::class.java)
    }

    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun previousQuote() = quoteList[--index]
}