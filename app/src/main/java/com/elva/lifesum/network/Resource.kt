package com.elva.lifesum.network

/**
 * Encapsulate the success/failed response result from server
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()
}
