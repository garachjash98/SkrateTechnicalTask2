package com.skrate.example.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skrate.example.repository.AppRepository

class ViewModelProviderFactory(
    val context: Context,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobDetailsViewModel::class.java)) {
            return JobDetailsViewModel(context,appRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}