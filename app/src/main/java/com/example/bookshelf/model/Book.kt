package com.example.bookshelf.model

data class Book(
    val id: String,
    val image: String,
    val hits: Int,
    val alias: String,
    val title: String,
    val lastChapterDate: Long,
    var isFavorite: Boolean = false // Add isFavorite property with a default value
)
