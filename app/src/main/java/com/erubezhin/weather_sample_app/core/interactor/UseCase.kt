package com.erubezhin.weather_sample_app.core.interactor

import com.erubezhin.weather_sample_app.core.platfrom.Response
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Response<Type>

    protected suspend fun invoke(params: Params, dispatcher: CoroutineDispatcher): Response<Type> {
        return withContext(dispatcher) {
            async { run(params) }.await()
        }
    }

}