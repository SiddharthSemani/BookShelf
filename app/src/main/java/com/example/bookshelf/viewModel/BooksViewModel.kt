package com.example.bookshelf.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookshelf.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BooksViewModel : ViewModel() {
    // LiveData for sorting options
    val sortByTitle = MutableLiveData<Boolean>(false)
    val sortByHits = MutableLiveData<Boolean>(false)
    val sortByFav = MutableLiveData<Boolean>(false)
    val bookList = MutableLiveData<List<Book>>() // LiveData for your book data

    init {
        loadBooksFromJson() // Load data from JSON file during initialization
    }

    // Load books from JSON file in assets folder
    private fun loadBooksFromJson() {
        val jsonString = application.assets.open("bookList.json").bufferedReader().use { it.readText() }
        val books = Gson().fromJson(jsonString, object : TypeToken<List<Book>>() {}.type)
        bookList.value = books
    }
}
