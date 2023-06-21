package com.example.udacity.ui

import com.example.udacity.data.Election

class ElectionItemClickListener(private val clickListener: (Election) -> Unit) {
    fun onClick(data: Election) = clickListener(data)
}