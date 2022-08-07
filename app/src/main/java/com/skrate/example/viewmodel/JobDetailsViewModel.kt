package com.skrate.example.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skrate.example.R
import com.skrate.example.adapter.HomeRecyclerViewItem
import com.skrate.example.repository.AppRepository
import com.skrate.example.utils.Resource
import com.skrate.example.utils.TAG
import com.skrate.example.utils.isOnline
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class JobDetailsViewModel(private val context: Context, private val appRepository: AppRepository) :
    ViewModel() {


    val liveData: MutableLiveData<Resource<List<HomeRecyclerViewItem>>> = MutableLiveData()

    init {
        getAllDetails()
    }

    private fun getAllDetails() = viewModelScope.launch {
        fetchDetails()
    }


    private suspend fun fetchDetails() {
        liveData.postValue(Resource.Loading())

        try {
            if (isOnline.checkInternetConnection(context)) {

                val response = appRepository.getJobs()
                liveData.postValue(handlePicsResponse(response))
            } else {
                liveData.postValue(Resource.Error(context.getString(R.string.no_internet_connection)))
            }
        } catch (t: Throwable) {

            when (t) {
                is IOException -> liveData.postValue(
                    Resource.Error(
                        context.getString(
                            R.string.network_failure
                        )
                    )
                )
                else -> liveData.postValue(
                    Resource.Error(
                        context.getString(
                            R.string.conversion_error
                        )
                    )
                )
            }
        }
    }

    private fun handlePicsResponse(response: Response<List<HomeRecyclerViewItem>>): Resource<List<HomeRecyclerViewItem>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.e(TAG, "handlePicsResponse: ------------$resultResponse)")
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }


}