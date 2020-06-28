package com.poc.bruna.brasileiraofeminino.base.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class UseCase<P, R> {

    abstract suspend fun execute(param: P? = null): (R)

    operator fun invoke(
        param: P? = null,
        onSuccess: (result: R) -> Unit = {},
        onError: (result: Exception) -> Unit = {}
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                onSuccess(execute(param))
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}