package com.example.udacity.data

import com.example.udacity.data.Address
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdministrationBody (
        val name: String? = null,
        val electionInfoUrl: String? = null,
        val votingLocationFinderUrl: String? = null,
        val ballotInfoUrl: String? = null,
        val correspondenceAddress: Address? = null
)