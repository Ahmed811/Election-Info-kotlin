package com.example.udacity.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.udacity.data.Election
import com.example.udacity.data.VoterInfo
import com.example.udacity.data.VoterInfoResponse
import com.example.udacity.local.SavedElectionDatabase
import com.example.udacity.local.VoterInfoDatabase
import com.example.udacity.remote.CivicsHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VoterInfoRepository(
    private val voterInfoDatabase: VoterInfoDatabase,
    private val savedElectionDatabase: SavedElectionDatabase,
    private val api: CivicsHttpClient) {

    private val _voterInfo = MutableLiveData<VoterInfo>()
    val voterInfo: LiveData<VoterInfo>
        get() = _voterInfo

    suspend fun getSavedElection(id: Int) : Election?{
        val election = withContext(Dispatchers.IO) {
            return@withContext savedElectionDatabase.get(id)
        }
        return election
    }
    suspend fun insertSavedElection(election: Election) {
        withContext(Dispatchers.IO) {
            savedElectionDatabase.insert(election)
        }
    }
    suspend fun deleteSavedElection(election: Election) {
        withContext(Dispatchers.IO) {
            savedElectionDatabase.delete(election)
        }
    }

    suspend fun refreshVoterInfo(address:String, id:Int) {
        withContext(Dispatchers.IO) {

            val response = api.getVoterInfo(address, id)
            val data = convertToVoterInfo(id, response)
            data?.run {
                voterInfoDatabase.insert(this)
            }
        }
    }

    private fun convertToVoterInfo(id: Int, response: VoterInfoResponse) : VoterInfo? {

        var voterInfo: VoterInfo? = null
        val state = response.state

        if (state?.isNotEmpty() == true) {
            val votingLocatinUrl: String? =
                state[0].electionAdministrationBody.votingLocationFinderUrl?.run {
                this
            }

            val ballotInfoUrl: String? =
                state[0].electionAdministrationBody.ballotInfoUrl?.run {
                this
            }

            voterInfo = VoterInfo(
                id,
                state[0].name,
                votingLocatinUrl,
                ballotInfoUrl
            )
        }

        return voterInfo
    }

    suspend fun loadVoterInfo(id:Int) {
        withContext(Dispatchers.IO) {
            val data = voterInfoDatabase.get(id)
            data?.run {_voterInfo.postValue(this)}
        }
    }
}