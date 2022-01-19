package com.griddynamics.weather_sample_app.core.interactor

import com.griddynamics.weather_sample_app.core.platfrom.RepositoryResponse
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): RepositoryResponse<Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (RepositoryResponse<Type>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onResult(deferred.await())
        }
    }

    class None
}