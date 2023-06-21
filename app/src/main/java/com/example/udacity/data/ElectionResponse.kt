package com.example.udacity.data

import com.example.udacity.data.Election
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ElectionResponse(
        val kind: String,
        val elections: List<Election>
)