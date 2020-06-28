package com.poc.bruna.brasileirao_feminino.plugin.extension

import com.poc.bruna.brasileirao_feminino.base.business.UseCase
import org.junit.Assert.fail

fun <P, R> UseCase<P, R>.assertError(param: P? = null, assertion: (Exception) -> Unit = {}) {
    invoke(
        param,
        onSuccess = { fail("Expected result error") },
        onError = assertion
    )
}

fun <P, R> UseCase<P, R>.assertSuccess(param: P? = null, assertion: (R) -> Unit = {}) {
    invoke(
        param,
        onSuccess = assertion,
        onError = { fail("Expected result success") }
    )
}
