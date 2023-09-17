package com.example.bookshelf.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookshelf.database.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE name = :name")
    fun getUserByName(name: String): User?


    // Add more queries as needed
}
