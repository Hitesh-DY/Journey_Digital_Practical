package com.journeydigitalpractical.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo

class Utils {
    companion object{
        const val BaseUrl = "https://jsonplaceholder.typicode.com/"

        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }
}