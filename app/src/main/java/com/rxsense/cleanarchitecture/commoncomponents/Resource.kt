package com.rxsense.cleanarchitecture.commoncomponents

/**
 * A generic class that contains data and status about loading this data.
 */

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()
    class Error<T>(val message: String? = "", val error: Throwable? = null, val data: T? = null) :
        Resource<T>()
}