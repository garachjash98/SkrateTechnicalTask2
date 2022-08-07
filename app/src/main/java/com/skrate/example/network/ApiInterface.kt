package com.skrate.example.network

import com.skrate.example.adapter.HomeRecyclerViewItem
import com.skrate.example.utils.Resource
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("bb11aecd-ba61-44b9-9e2c-beabc442d818")
    suspend fun getJobDetails(): Response<List<HomeRecyclerViewItem>>


}