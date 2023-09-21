package com.erubezhin.weather_sample_app.core.interactor

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()

    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(val throwable: Throwable) : DataState<Nothing>()
}
