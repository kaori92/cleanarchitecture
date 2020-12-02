package com.example.cleanarchitecture.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class DefaultConnectivityCheckerTest : Spek({

    val context by memoized {
        mock<Context>()
    }

    val connectivityManager by memoized {
        mock<ConnectivityManager>()
    }

    val network by memoized {
        mock<Network>()
    }

    val networkCapabilities by memoized {
        mock<NetworkCapabilities>()
    }

    val connectivityChecker by memoized {
        DefaultConnectivityChecker(context)
    }

    var result = false

    describe("checking is online"){

        beforeEachTest {
            given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager)

            given(connectivityManager.activeNetwork).willReturn(network)
        }

        context("when is connected to cellular"){
            beforeEachTest {
                given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager)
                given(connectivityManager.activeNetwork).willReturn(network)
                given(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)).willReturn(true)
                given(connectivityManager.getNetworkCapabilities(network)).willReturn(networkCapabilities)
                result = connectivityChecker.isOnline()
            }

            it("should return true"){
                assertEquals(true, result)
            }
        }

        context("when is connected to wifi"){
            beforeEachTest {
                given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager)
                given(connectivityManager.activeNetwork).willReturn(network)
                given(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)).willReturn(true)
                given(connectivityManager.getNetworkCapabilities(network)).willReturn(networkCapabilities)
                result = connectivityChecker.isOnline()
            }

            it("should return true"){
                assertEquals(true, result)
            }
        }

        context("when is connected to ethernet"){
            beforeEachTest {
                given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager)
                given(connectivityManager.activeNetwork).willReturn(network)
                given(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)).willReturn(true)
                given(connectivityManager.getNetworkCapabilities(network)).willReturn(networkCapabilities)
                result = connectivityChecker.isOnline()
            }

            it("should return true"){
                assertEquals(true, result)
            }
        }

        context("when is connected to another transport"){
            beforeEachTest {
                given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager)
                given(connectivityManager.activeNetwork).willReturn(network)
                given(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)).willReturn(true)
                given(connectivityManager.getNetworkCapabilities(network)).willReturn(networkCapabilities)
                result = connectivityChecker.isOnline()
            }

            it("should return false"){
                assertEquals(false, result)
            }
        }
    }
})