package com.example.bookshelf.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bookshelf.database.AppDatabase
import com.example.bookshelf.viewModel.LoginViewModel


class LoginViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

