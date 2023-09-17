package com.example.bookshelf.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookshelf.viewModel.BooksViewModel
import com.example.bookshelf.databinding.FragmentBooksBinding

class BooksFragment : Fragment() {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var viewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Observe sorting options LiveData
        viewModel.sortByTitle.observe(viewLifecycleOwner) { sortByTitle ->
            // Handle sorting logic here
        }
        viewModel.sortByHits.observe(viewLifecycleOwner) { sortByHits ->
            // Handle sorting logic here
        }
        viewModel.sortByFav.observe(viewLifecycleOwner) { sortByFav ->
            // Handle sorting logic here
        }

        binding.recyclerView.adapter = bookAdapter

        viewModel.bookList.observe(viewLifecycleOwner) { books ->
            bookAdapter.bookList = books
        }

        return binding.root
    }
}
