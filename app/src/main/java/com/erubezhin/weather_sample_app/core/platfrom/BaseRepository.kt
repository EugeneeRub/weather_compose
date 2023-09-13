package com.erubezhin.weather_sample_app.core.platfrom

import retrofit2.Call
import java.lang.Exception

open class BaseRepository {

    protected fun <T> request(call: Call<T>): Response<T> {
        return try {
            val response = call.execute()
            when (response.isSuccessful && response.body() != null) {
                true -> Response.Result(response.body()!!)
                false -> Response.Error(Exception(response.message().toString()))
            }
        } catch (exception: Throwable) {
            Response.Error(exception)
        }
    }
}

sealed class Response<out T> {
    data class Result<T>(val result: T) : Response<T>()
    data class Error<T>(val throwable: Throwable) : Response<T>()
}