package com.example.bookshelf.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val country: String,
    val password: String // Store the password (for demonstration purposes only)
)