package com.example.udacity.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.udacity.data.Representative
import com.example.udacity.remote.CivicsHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepresentativesRepository(private val api: CivicsHttpClient) {

    private val _representatives = MutableLiveData<List<Representative>?>()
    val representatives: LiveData<List<Representative>?>
        get() = _representatives

    suspend fun refreshRepresentatives(addressStr: String) {
        withContext(Dispatchers.IO) {
            _representatives.postValue(null)
            val representativeResponse = CivicsHttpClient.getRepresentatives(addressStr)

            val representativeList = representativeResponse.offices.flatMap { office ->
                office.getRepresentatives(representativeResponse.officials)
            }

            _representatives.postValue(representativeList as MutableList<Representative>?)
        }
    }
}