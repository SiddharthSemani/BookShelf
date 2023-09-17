package com.example.bookshelf.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.bookshelf.database.AppDatabase
import com.example.bookshelf.R
import com.example.bookshelf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
    }

}