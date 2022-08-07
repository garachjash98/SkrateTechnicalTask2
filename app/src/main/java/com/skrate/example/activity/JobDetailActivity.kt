package com.skrate.example.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skrate.example.adapter.HomeRecyclerViewItem
import com.skrate.example.adapter.JobDetailsAdapter
import com.skrate.example.databinding.ActivityJobDetailBinding
import com.skrate.example.repository.AppRepository
import com.skrate.example.utils.Resource
import com.skrate.example.utils.TAG
import com.skrate.example.utils.showToastMsg
import com.skrate.example.viewmodel.JobDetailsViewModel
import com.skrate.example.viewmodel.ViewModelProviderFactory
import kotlin.system.exitProcess

class JobDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobDetailBinding

    private lateinit var viewModel: JobDetailsViewModel
    private lateinit var adapter: JobDetailsAdapter
    private var arrayList = ArrayList<HomeRecyclerViewItem>()

    //    var items = ArrayList<HomeRecyclerViewItem>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



        setupUi()


    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        exitProcess(0)
    }

    private fun setupUi() {
        binding.apply {
            rvJobDetails.setHasFixedSize(true)
            rvJobDetails.layoutManager = LinearLayoutManager(this@JobDetailActivity)
            adapter = JobDetailsAdapter(arrayList)
            binding.rvJobDetails.adapter = adapter
            setupViewModel()
        }

    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(this@JobDetailActivity, repository)
        viewModel = ViewModelProvider(this, factory)[JobDetailsViewModel::class.java]
        getApiResponse()
    }

    private fun getApiResponse() {
        viewModel.liveData.observe(this) { dataItems ->
            when (dataItems) {
                is Resource.Success -> {
                    hideProgressBar()
                    dataItems.data?.let { picsResponse ->

                        arrayList.addAll(picsResponse)
                    }


                }
                is Resource.Error -> {
                    hideProgressBar()
                    dataItems.message?.let { message ->
                        showToastMsg(message)
                        Log.e(TAG, "getApiResponse: -------1${dataItems.data}")
                        Log.e(TAG, "getApiResponse: -------2${dataItems.message}")

                    }


                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }


}