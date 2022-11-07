package com.gricsan.caradaro.core.network

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {

    class Success<T>(
        data: T?,
        message: String? = null,
        code: Int? = null
    ) : NetworkResult<T>(data, message, code)

    class Failure<T>(
        data: T? = null,
        message: String? = null,
        code: Int? = null
    ) : NetworkResult<T>(data, message, code)

}
