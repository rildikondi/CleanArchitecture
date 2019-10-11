package com.akondi.cleanarchitecure.core.interactor

import com.akondi.cleanarchitecure.core.exception.Failure
import com.akondi.cleanarchitecure.core.functional.Either
import kotlinx.coroutines.*


abstract class UseCase<out Type, in Params> where Type : Any {

    // When we switch to many times from a thread to another we can use  CoroutineScope
    // also when  you need the coroutine to live longer than the calling scope
    //private val scope = CoroutineScope(Dispatchers.Default)

    abstract suspend fun run(params: Params): Either<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        coroutineScope {
            val job = withContext(Dispatchers.Default) {
                async {
                    run(params)
                }
            }

            withContext(Dispatchers.Main) {
                launch {
                    onResult(job.await())
                }
            }
        }
   }

    class None
}
