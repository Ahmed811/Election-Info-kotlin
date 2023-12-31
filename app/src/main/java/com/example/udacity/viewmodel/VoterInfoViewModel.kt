package com.example.udacity.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.udacity.R
import com.example.udacity.data.Election
import com.example.udacity.data.VoterInfo
import com.example.udacity.local.SavedElectionDatabase
import com.example.udacity.local.VoterInfoDatabase
import com.example.udacity.remote.CivicsHttpClient
import com.example.udacity.repository.VoterInfoRepository
import kotlinx.coroutines.launch

class VoterInfoViewModel(app: Application): BaseViewModel(app) {

    companion object {
        private const val DEFAULT_STATE = "la"
    }

    private val repository = VoterInfoRepository(
        VoterInfoDatabase.getInstance(app),
        SavedElectionDatabase.getInstance(app),
        CivicsHttpClient
    )

    private val _selectedElection = MutableLiveData<Election>()
    val selectedElection : LiveData<Election>
        get() = _selectedElection

    val voterInfo = repository.voterInfo

    private val _isElectionSaved = MutableLiveData<Boolean?>()
    val isElectionSaved : LiveData<Boolean?>
        get() = _isElectionSaved

    private val mockData = true
    val mockVoterInfo = MutableLiveData<VoterInfo>()

    init {
        if(mockData) {
            val data = VoterInfo(
                2000,
                "State XYZ",
                "",
                "")
            mockVoterInfo.postValue(data)
        }

        _isElectionSaved.value = null
    }

    fun refresh(data: Election) {
        _selectedElection.value = data
        refreshIsElectionSaved(data)
        refreshVoterInfo(data)
    }

    private fun refreshIsElectionSaved(data: Election) {
        viewModelScope.launch {
            try {
                val savedElection = repository.getSavedElection(data.id)
                _isElectionSaved.postValue(savedElection != null)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun refreshVoterInfo(data: Election) {
        viewModelScope.launch {
            try {
                val state = if(data.division.state.isEmpty()) DEFAULT_STATE else data.division.state
                val address = "${state},${data.division.country}"

                repository.refreshVoterInfo(address, data.id)
                repository.loadVoterInfo(data.id)

            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBarInt.postValue(R.string.fail_no_network_msg)
                repository.loadVoterInfo(data.id)
            }
        }
    }
    fun onFollowButtonClick() {
        viewModelScope.launch {
            _selectedElection.value?.let {
                if(isElectionSaved.value == true) {
                    repository.deleteSavedElection(it)
                } else {
                    repository.insertSavedElection(it)
                }
                refreshIsElectionSaved(it)
            }
        }
    }
}