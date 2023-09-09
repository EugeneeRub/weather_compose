package com.erubezhin.weather_sample_app.core.platfrom

import com.erubezhin.weather_sample_app.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_PATH)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor { chain ->
                    val requestChain = chain.request()
                    val httpUrl = requestChain.url()

                    val url = httpUrl
                        .newBuilder()
                        .addQueryParameter("appid", BuildConfig.API_KEY).build()

                    val request = requestChain.newBuilder().url(url).build()

                    chain.proceed(request)
                }.build()
        )
        .build()
}