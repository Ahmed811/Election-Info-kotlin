package com.example.udacity.data

import com.example.udacity.data.Address

data class Official (
    val name: String,
    val address: List<Address>? = null,
    val party: String? = null,
    val phones: List<String>? = null,
    val urls: List<String>? = null,
    val photoUrl: String? = null,
    val channels: List<Channel>? = null
)