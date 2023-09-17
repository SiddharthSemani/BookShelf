package com.example.bookshelf.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookshelf.databinding.FragmentLoginBinding
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.viewModel.LoginNavigation
import com.example.bookshelf.viewModel.LoginViewModel
import com.example.bookshelf.viewModelFactory.LoginViewModelFactory
import com.example.bookshelf.database.MyApplication
import com.example.bookshelf.R


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        val database = (requireActivity().application as MyApplication).database
        val viewModelFactory = LoginViewModelFactory(database)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        // Bind the ViewModel to the layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Set up navigation to the next screen (e.g., the book list screen)
        viewModel.navigation.observe(viewLifecycleOwner) { navigation ->
            when (navigation) {
                is LoginNavigation.NavigateToNextScreen -> {
                    // Navigate to the next screen (e.g., the book list screen)
                    findNavController().navigate(R.id.action_loginFragment_to_nextFragment)
                    viewModel.navigateTo(LoginNavigation.None) // Reset the navigation action
                }
                is LoginNavigation.NavigateToSignUpScreen -> {
                    // Navigate to the sign-up screen
                    findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
                    viewModel.navigateTo(LoginNavigation.None) // Reset the navigation action
                }
                // Handle other navigation actions if needed
            }
        }

        binding.btnGoToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}

