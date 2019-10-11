package com.akondi.cleanarchitecure.core.util

class IdleTimer(private val idleTimeoutSeconds: Int, private val listener: IdleTimeListener) :
    Thread() {

    override fun run() {
        for (second in idleTimeoutSeconds downTo 0) {

            if (second == 0) {
                listener.onTimePassed()
            }
            try {
                sleep(1000)
            } catch (ignored: InterruptedException) {
                return
            }

        }
    }

    companion object {
        private val TAG = "IdleTimer"
    }
}

