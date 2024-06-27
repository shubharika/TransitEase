package com.android.njtransit.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {
    //API call for registering the user
    @POST("api/ping")
    fun pingServer(@Body parameters:Map<String, String>): Call<String>
}