package com.example.moviescity.utils

import retrofit2.Response

data class NetworkResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {
    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }

    companion object {
        fun <T> success(data: Response<T>): NetworkResponse<T> {
            return NetworkResponse(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): NetworkResponse<T> {
            return NetworkResponse(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    val isFailed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !isFailed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!
}
