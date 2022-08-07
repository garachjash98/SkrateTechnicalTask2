package com.skrate.example.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


const val TAG = "SKRATEDEMO"

fun Context.showToastMsg(msg: CharSequence) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

object isOnline {
    fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
