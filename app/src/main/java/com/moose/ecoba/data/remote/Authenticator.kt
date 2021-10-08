package com.moose.ecoba.data.remote

import com.moose.ecoba.EcobaApplication
import com.moose.ecoba.data.PreferencesHelper
import dagger.hilt.android.EntryPointAccessors
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

object Authenticator: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val context = EcobaApplication.appContext
        val entryPoint = EntryPointAccessors.fromApplication(context, PreferencesHelper::class.java)

        val originalRequest = chain.request()
        val token = entryPoint.preferences().getToken()

        val newRequest = originalRequest.newBuilder().apply {
            val headers = Headers.Builder()
            headers.addAll(originalRequest.headers)
            headers.add("Authorization", "Bearer $token")
            headers(headers.build())
        }.build()
        return chain.proceed(newRequest)
    }
}