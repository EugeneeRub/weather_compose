package com.griddynamics.weather_sample_app.core.platfrom

import retrofit2.Call
import java.lang.Exception

open class BaseRepository {

    protected fun <T> request(call: Call<T>): RepositoryResponse<T> {
        return try {
            val response = call.execute()
            when (response.isSuccessful && response.body() != null) {
                true -> RepositoryResponse.Result(response.body()!!)
                false -> RepositoryResponse.Error(Exception(response.message().toString()))
            }
        } catch (exception: Throwable) {
            RepositoryResponse.Error(exception)
        }
    }
}

sealed class RepositoryResponse<out T> {
    data class Result<T>(val result: T) : RepositoryResponse<T>()
    data class Error<T>(val throwable: Throwable) : RepositoryResponse<T>()
}