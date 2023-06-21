package com.example.udacity.remote

import com.example.udacity.data.ElectionResponse
import com.example.udacity.data.RepresentativeResponse
import com.example.udacity.data.VoterInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ICivicsApiService {

    @GET("elections")
    suspend fun getElections(): ElectionResponse

    @GET("elections")
    suspend fun getElectionsJsonStr(): String

    // Documentation: https://developers.google.com/civic-information/docs/v2/elections/voterInfoQuery
    // Example: https://www.googleapis.com/civicinfo/v2/voterinfo?key=<API KEY>&address=la,us&electionId=2000
    @GET("voterinfo")
    suspend fun getVoterInfo(
        @Query("address") address: String,
        @Query("electionId") electionId: Int
    ): VoterInfoResponse

    @GET("voterinfo")
    suspend fun getVoterInfoJsonStr(
        @Query("address") address: String,
        @Query("electionId") electionId: Int
    ): String

    // Documentation: https://developers.google.com/civic-information/docs/v2/representatives/representativeInfoByAddress
    // Example: https://www.googleapis.com/civicinfo/v2/representatives?key=<API KEY>&address=la
    @GET("representatives")
    suspend fun getRepresentatives(
        @Query("address") address: String
    ): RepresentativeResponse

    @GET("representatives")
    suspend fun getRepresentativesJsonStr(
        @Query("address") address: String
    ): String
}