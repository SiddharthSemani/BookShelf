package com.example.bookshelf.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.database.AppDatabase
import com.example.bookshelf.database.User
import com.example.bookshelf.model.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val selectedCountry = MutableLiveData<String>()

    private val _nameError = MutableLiveData<String?>()
    val nameError: MutableLiveData<String?> = _nameError

    // LiveData for password error message
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: MutableLiveData<String?> = _passwordError

    // SingleLiveEvent for navigation (e.g., moving to the next screen)
    val _navigateToNextScreen = MutableLiveData<Boolean>()
    val navigateToNextScreen: LiveData<Boolean>
        get() = _navigateToNextScreen

    var database : AppDatabase? = null
    private val userDao = database?.userDao()

    init {
        loadCountriesFromJson()
    }

    private fun loadCountriesFromJson() {
        val json: String
        val countriesList = mutableListOf<Country>()

        try {
            val assets = getApplication<Application>().assets
            val inputStream = assets.open("countries.json") // Replace with your JSON file name
            json = inputStream.bufferedReader().use { it.readText() }

            val type: Type = object : TypeToken<Map<String, Country>>() {}.type
            val countriesMap: Map<String, Country> = Gson().fromJson(json, type)
            countriesList.addAll(countriesMap.values)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        _countries.value = countriesList
    }

    fun isNameValid(name: String): Boolean {
        // Name validation logic (e.g., not empty)
        return !name.isEmpty()
    }

    fun isPasswordValid(password: String): Boolean {
        // Password validation logic
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[!@#\$%&()])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
        return password.matches(passwordRegex)
    }


    fun onSignUpClick(name: String, password: String, selectedCountry: String) {
        if (!isNameValid(name)) {
            // Set name error message
            _nameError.value = "Please enter a valid name"
            return
        } else {
            // Clear name error message if valid
            _nameError.value = null
        }

        if (!isPasswordValid(password)) {
            // Set password error message
            _passwordError.value = "Password must meet the criteria"
            return
        } else {
            // Clear password error message if valid
            _passwordError.value = null
        }

        viewModelScope.launch {
            insertUser(User(name = name, password = password, country = selectedCountry))
        }
        _navigateToNextScreen.value = true
    }

    private suspend fun insertUser(user: User) {
        userDao?.insert(user)
    }

}