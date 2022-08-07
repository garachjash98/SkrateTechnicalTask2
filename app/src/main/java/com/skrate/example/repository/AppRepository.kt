package com.skrate.example.repository

import com.skrate.example.network.RetrofitClient

class AppRepository {


    suspend fun getJobs() = RetrofitClient.apiInterface.getJobDetails()


}