package com.example.bookshelf.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookshelf.database.User
import com.example.bookshelf.database.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}