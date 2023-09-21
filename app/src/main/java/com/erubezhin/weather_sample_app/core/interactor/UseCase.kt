package com.erubezhin.weather_sample_app.core.interactor

import com.erubezhin.weather_sample_app.core.platfrom.Response
import kotlinx.coroutines.*

abstract class UseCase<Type, in Params> where Type : Any {
    protected suspend fun invoke(
        dispatcher: CoroutineDispatcher,
        func: suspend () -> Response<Type>,
    ): Response<Type> {
        return withContext(dispatcher) {
            async { func() }.await()
        }
    }
}
