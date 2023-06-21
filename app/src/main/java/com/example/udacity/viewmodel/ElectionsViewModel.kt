package com.example.udacity.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.udacity.R
import com.example.udacity.data.Division
import com.example.udacity.data.Election
import com.example.udacity.local.ActiveElectionDatabase
import com.example.udacity.local.SavedElectionDatabase
import com.example.udacity.remote.CivicsHttpClient
import com.example.udacity.repository.ElectionsRepository
import kotlinx.coroutines.launch
import java.util.*

class ElectionsViewModel(app: Application) : BaseViewModel(app) {

    private val repository = ElectionsRepository(
        ActiveElectionDatabase.getInstance(app),
        SavedElectionDatabase.getInstance(app),
        CivicsHttpClient)

    val activeElections = repository.activeElections
    val savedElections = repository.savedElections

    private val mockData = false
    private val _mockElections = MutableLiveData<List<Election>>()
    val mockElections : LiveData<List<Election>>
        get() = _mockElections

    init {
        if(mockData) {
            mockElections()
        } else {
            refreshElections()
        }
    }

    private fun refreshElections() {
        viewModelScope.launch {
            try {
                repository.refreshElections()

            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBarInt.postValue(R.string.fail_no_network_msg)
            }
        }
    }

    private fun mockElections() {

        val mockElections = mutableListOf<Election>()

        var count = 1
        while (count <= 10) {

            val data = Election(
                count,
                "name:$count",
                Date(),
                Division("id", "US", "state")
            )

            mockElections.add(data)

            ++count
        }

        _mockElections.postValue(mockElections)
    }
}