package com.elva.lifesum.network

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        //todo
        /**
         * here I remove headers for the reason that if I request with the header using the given
         * Auth token, the server will response 401 error code.
         */
       // val headers: Headers = original.headers.newBuilder().add("Authorization", AUTH_TOKEN).build()
        val request = original.newBuilder()
          //  .headers(headers)
            .method(original.method, original.body)
            .build()
       return chain.proceed(request)
    }
}