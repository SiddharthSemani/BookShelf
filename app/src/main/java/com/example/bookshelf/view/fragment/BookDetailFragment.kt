package com.example.bookshelf.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookshelf.R
import com.example.bookshelf.model.Book
import com.example.bookshelf.viewModel.BookDetailViewModel

class BookDetailFragment : Fragment() {

    private lateinit var viewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        // Retrieve book details from arguments
        val book: Book? = arguments?.getParcelable("book")

        // Populate UI elements with book details
        if (book != null) {
            textViewTitle.text = book.title
            textViewSummary.text = book.summary
            // Load book cover image (you can use an image loading library like Glide)
            // imageViewCover.load(book.imageUrl)
        }
    }
}
