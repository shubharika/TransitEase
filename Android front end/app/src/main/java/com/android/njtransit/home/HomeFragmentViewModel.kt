package com.android.njtransit.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.njtransit.database.AppDatabase
import com.android.njtransit.database.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val repository: HomeFragmetnRepository): ViewModel(){

    private var response = MutableLiveData<String>()

    fun pingUser(user: String, timestamp: String, trainId:String){
        viewModelScope.launch {
            response = repository.pingServer(user, timestamp, trainId)
        }
    }

    fun checkResponse():MutableLiveData<String>{
        return response
    }
}