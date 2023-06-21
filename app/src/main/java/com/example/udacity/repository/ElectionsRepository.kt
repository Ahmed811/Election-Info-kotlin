package com.example.udacity.repository

import com.example.udacity.local.ActiveElectionDatabase
import com.example.udacity.local.SavedElectionDatabase
import com.example.udacity.remote.CivicsHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ElectionsRepository(
    private val activeElectionDatabase: ActiveElectionDatabase,
    savedElectionDatabase: SavedElectionDatabase,
    private val api: CivicsHttpClient) {

    val activeElections = activeElectionDatabase.getAll()
    val savedElections = savedElectionDatabase.getAll()

    suspend fun refreshElections() {
        withContext(Dispatchers.IO) {
            val electionResponse = api.getElections()
            activeElectionDatabase.insertAll(electionResponse.elections)
        }
    }
}