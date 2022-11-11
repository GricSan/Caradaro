package com.gricsan.caradaro.base.domain.models

sealed class Result<T> {

    class Success<T>(
        val data: T
    ) : Result<T>()

    class Error<T>(
        val message: String,
        val data: T? = null
    ) : Result<T>()

    class Loading<T>(
        val data: T? = null
    ) : Result<T>()

}
