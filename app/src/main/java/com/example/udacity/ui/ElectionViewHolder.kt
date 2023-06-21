package com.example.udacity.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.udacity.data.Election
import com.example.udacity.databinding.ElectionListItemBinding


class ElectionViewHolder private constructor(private val binding: ElectionListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Election, clickListener: ElectionItemClickListener) {
        binding.election = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup) : ElectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ElectionListItemBinding.inflate(layoutInflater, parent, false)
            return ElectionViewHolder(binding)
        }
    }
}