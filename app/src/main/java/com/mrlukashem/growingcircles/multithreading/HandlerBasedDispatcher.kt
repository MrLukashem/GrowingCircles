package com.mrlukashem.mediacontentprovider.multithreading

import android.os.Handler
import android.os.Message

/**
 * Created by MrLukashem on 20.03.2018.
 */
abstract class HandlerBasedDispatcher<T> : Dispatcher<T>{
    protected abstract var handler: Handler
    private val callbacks = LinkedHashMap<Int, Pair<Task<T>, Callback<T>>>()
    private var callbackID = 0
    private var isBegan = false

    override fun dispatch(task: Task<T>, cb: Callback<T>) {
        ifBegan {
            handler.sendMessage(prepareMessage(task, cb))
        }
    }

    private fun ifBegan(block: () -> Unit) = if (isBegan) {
        block()
    } else {
        throw DispatcherNotRunningException()
    }

    override fun begin() {
        isBegan = true
    }

    override fun quit() {
        isBegan = false
    }

    override fun isAlive(): Boolean = isBegan

    private fun prepareMessage(task: Task<T>, callback: Callback<T>): Message {
        synchronized(this) {
            val msg = Message()
            msg.what = callbackID
            callbacks[callbackID] = Pair(task, callback)

            ++callbackID
            return msg
        }
    }

    protected fun internalHandleMessage(msg: Message): Boolean {
        synchronized(this) {
            val dataID = msg.what
            val data = callbacks[dataID]
            return if (data != null) {
                val (task, cb) = data

                val result = task()
                cb(result)

                callbacks.remove(dataID)
                true
            } else {
                false
            }
        }
    }

    class DispatcherNotRunningException
        : IllegalStateException("The Dispatcher thread is not running!")

    class DispatcherStillRunningException
        : IllegalStateException("The Dispatcher thread still running!")
}