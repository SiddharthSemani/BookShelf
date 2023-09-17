package com.example.bookshelf.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.database.MyApplication
import com.example.bookshelf.R
import com.example.bookshelf.viewModel.SignUpViewModel
import com.example.bookshelf.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.database = (requireActivity().application as MyApplication).database

        viewModel.navigateToNextScreen.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                // Navigate to the next screen (e.g., the book list screen)
//                findNavController().navigate(R.id.action_signUpFragment_to_nextFragment)
                viewModel._navigateToNextScreen.value = false
            }
        }

        binding.btnGoToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }


        return binding.root
    }
}
