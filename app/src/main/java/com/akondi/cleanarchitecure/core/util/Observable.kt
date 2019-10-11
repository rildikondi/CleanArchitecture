package com.akondi.cleanarchitecure.core.util

class Observable<T> {

    private var observers = emptyList<(T) -> Unit>()

    fun addObserver(observer: (T) -> Unit) {
        observers += observer
    }

    fun clearObservers() {
        observers = emptyList()
    }

    fun callObservers(newValue: T) {
        observers.forEach { it(newValue) }
    }

}