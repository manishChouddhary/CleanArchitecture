package com.rxsense.cleanarchitecture.network

import com.rxsense.cleanarchitecture.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = BuildConfig.apiKey
        requestBuilder.addHeader(
            "app-id",
            token
        )
        return chain.proceed(requestBuilder.build())
    }
}
