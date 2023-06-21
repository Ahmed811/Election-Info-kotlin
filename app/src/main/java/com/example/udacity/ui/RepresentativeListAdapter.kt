package com.example.udacity.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.udacity.data.Representative

class RepresentativeListAdapter
    : ListAdapter<Representative, RepresentativeViewHolder>(RepresentativeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepresentativeViewHolder {
        return RepresentativeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RepresentativeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}