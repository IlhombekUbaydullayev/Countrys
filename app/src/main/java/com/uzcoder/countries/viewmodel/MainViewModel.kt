package com.uzcoder.countries.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzcoder.countries.database.DataRepository
import com.uzcoder.countries.database.entity.SaveData
import com.uzcoder.countries.model.WelcomeElement
import com.uzcoder.countries.network.service.CountryService
import com.uzcoder.countries.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val countryService: CountryService,private val repository: DataRepository) : ViewModel() {

    val subscribers = repository.subscribers

    val allCountry = MutableLiveData<ArrayList<WelcomeElement>?>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    fun apiPostList() {
        countryService.getAll().enqueue(object : Callback<ArrayList<WelcomeElement>> {
            override fun onResponse(
                call: Call<ArrayList<WelcomeElement>>,
                response: Response<ArrayList<WelcomeElement>>,
            ) {
                allCountry.value = response.body()
                Log.d("MainViewModel",allCountry.value.toString())
            }

            override fun onFailure(call: Call<ArrayList<WelcomeElement>>, t: Throwable) {
                allCountry.value = null
                statusMessage.value = Event("Server Did Not Response")
                Log.d("MainViewModel",allCountry.value.toString())
            }

        })
    }

    val allCountryByName = MutableLiveData<ArrayList<WelcomeElement>>()
    fun apiGetByName(name : String) {
        countryService.getByName(name).enqueue(object : Callback<ArrayList<WelcomeElement>> {
            override fun onResponse(
                call: Call<ArrayList<WelcomeElement>>,
                response: Response<ArrayList<WelcomeElement>>,
            ) {
                if (response.body() != null) {
                    allCountryByName.value = response.body()
                    Log.d("MainViewModel", allCountryByName.value.toString())
                }else {
                    allCountryByName.value = ArrayList()
                    statusMessage.value = Event("Wrong search!")
                }
            }

            override fun onFailure(call: Call<ArrayList<WelcomeElement>>, t: Throwable) {
                allCountryByName.value = ArrayList()
                Log.d("MainViewModelWrong",allCountryByName.value.toString())
                statusMessage.value = Event("Server Did Not Response")
            }

        })
    }

    fun insert(subscriber: SaveData): Job = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)
//        if (newRowId > -1) {
//            statusMessage.value = Event("Omadli yakunlandi")
//        } else {
//            statusMessage.value = Event("Error Occurred")
//        }

    }

    // basic workflow to delete all data
    fun clearAll(): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
    }

}