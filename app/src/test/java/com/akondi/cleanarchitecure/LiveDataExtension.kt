package com.akondi.cleanarchitecure

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeForTesting(
    block: () -> Unit
) {
    val observer = Observer<T> { Unit }
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}