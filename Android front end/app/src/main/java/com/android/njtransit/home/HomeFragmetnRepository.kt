package com.android.njtransit.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.njtransit.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeFragmetnRepository @Inject constructor(private val apiClient: APIClient){
    fun pingServer(user: String,  timestamp: String, trainId: String): MutableLiveData<String> {
        val result = MutableLiveData<String>()
        val params: MutableMap<String, String> = HashMap()
        params["user"] = user
        params["timestamp"] = timestamp
        params["trainId"] = trainId
        apiClient.pingServer(params).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("RESPONSE",response.body().toString())
                if(response.isSuccessful){
                    result.postValue("true")
                }
                else{
                    result.postValue("false")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { Log.e("Callback", it) }
                result.postValue("false")
            }

        })
        return result
    }
}