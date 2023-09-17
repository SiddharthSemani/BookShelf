package com.example.bookshelf.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.model.Book
import com.example.bookshelf.databinding.ItemBookBinding

class BookAdapter(private val onBookClickListener: (Book) -> Unit) :
    ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

    var bookList: List<Book> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)

        // Set an OnClickListener to handle item clicks
        holder.itemView.setOnClickListener { onBookClickListener(book) }
    }

    // DiffUtil callback for efficient updates
    private class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}

class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book) {
        binding.book = book
        binding.executePendingBindings()
    }
}
