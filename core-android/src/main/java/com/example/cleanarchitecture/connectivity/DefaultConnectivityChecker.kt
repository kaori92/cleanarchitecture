package com.example.cleanarchitecture.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class DefaultConnectivityChecker(
    private val context: Context
): ConnectivityChecker {
    override fun isOnline(): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let {
            val capabilities = it.getNetworkCapabilities(it.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}