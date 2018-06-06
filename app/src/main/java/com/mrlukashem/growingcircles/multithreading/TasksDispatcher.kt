package com.mrlukashem.mediacontentprovider.multithreading

import android.os.ConditionVariable
import android.os.Handler
import android.os.Looper

/**
 * Created by MrLukashem on 20.03.2018.
 */
class TasksDispatcher<T> : HandlerBasedDispatcher<T>(), Runnable {
    override lateinit var handler: Handler
    private lateinit var looper: Looper
    private val myThread = Thread(this)
    private val handlerInitializedLock = ConditionVariable()

    override fun run() {
        Looper.prepare()
        looper = Looper.myLooper()

        handler = Handler({ internalHandleMessage(it) })

        handlerInitializedLock.open()
        Looper.loop()
    }

    override fun begin() {
        synchronized(this) {
            ifNotAliveOrThrow {
                handlerInitializedLock.close()
                myThread.start()
                handlerInitializedLock.block()

                super.begin()
            }
        }
    }

    private fun ifNotAliveOrThrow(block: () -> Unit) {
        if (!myThread.isAlive) block() else DispatcherStillRunningException()
    }

    override fun quit() {
        synchronized(this) {
            super.quit()
            if (isAlive()) {
                looper.quitSafely()
            }
        }
    }

    override fun isAlive(): Boolean = myThread.isAlive
}