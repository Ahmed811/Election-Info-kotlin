package com.example.udacity.data

import com.example.udacity.data.Office
import com.squareup.moshi.Json

data class RepresentativeResponse(
        val offices: List<Office>,
        val officials: List<Official>
)