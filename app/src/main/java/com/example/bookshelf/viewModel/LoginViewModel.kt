package com.example.bookshelf.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.database.AppDatabase
import kotlinx.coroutines.launch

class LoginViewModel(private val database: AppDatabase) : ViewModel() {
    // LiveData for username and password
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    // LiveData for error messages
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    // LiveData for controlling the visibility of error message TextView
    val errorMessageVisible: LiveData<Int> = _errorMessage.map {
        if (it.isNullOrEmpty()) {
            // Hide the error message TextView when the error message is empty
            View.GONE
        } else {
            // Show the error message TextView when there's an error message
            View.VISIBLE
        }
    }

    // LiveData for enabling/disabling the login button
    val isLoginButtonEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        fun update() {
            value = !username.value.isNullOrEmpty() && (password.value?.length ?: 0) >= 8
        }

        addSource(username) { update() }
        addSource(password) { update() }
    }

    fun loginUser() {
        val enteredUsername = username.value ?: ""
        val enteredPassword = password.value ?: ""

        if (enteredUsername.isEmpty() || enteredPassword.length < 8) {
            _errorMessage.value = "Invalid username or password"
            return
        }

        viewModelScope.launch {
            val user = database.userDao().getUserByName(enteredUsername)
            if (user != null && user.password == enteredPassword) {
                // Successful login
                _navigate(LoginNavigation.NavigateToNextScreen)
            } else {
                _errorMessage.value = "Invalid username or password"
            }
        }
    }

    private val _navigation = MutableLiveData<LoginNavigation>()
    val navigation: LiveData<LoginNavigation>
        get() = _navigation

    // Function to trigger navigation
    fun navigateTo(action: LoginNavigation) {
        _navigation.value = action
    }
}

sealed class LoginNavigation {
    object NavigateToNextScreen : LoginNavigation()
    object NavigateToSignUpScreen : LoginNavigation()
}

