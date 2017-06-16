package com.jpp.kotlin.weatherapp.extentions


fun <K, V : Any> MutableMap<K, V?>.toVarargArray():
        Array<out Pair<K, V>> = map ({Pair(it.key, it.value!!)}).toTypedArray()

/**
 * Function that iterates over a collection and executes the predicate over each element.
 * When the result of the predicate function is not null, this result is returned.
 * The function receives a predicate which gets an object of type T and returns a value of type
 * R? (might be null).
 */
inline fun <T, R: Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for(element in this) {
        val result = predicate(element)
        if(result != null) return result
    }
    throw NoSuchElementException("No element matching the predicate was found")
}