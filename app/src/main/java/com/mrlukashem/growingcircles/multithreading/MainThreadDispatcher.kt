package com.mrlukashem.mediacontentprovider.multithreading

import android.os.Handler
import android.os.Looper

/**
 * Created by MrLukashem on 20.03.2018.
 */
class MainThreadDispatcher<T> : HandlerBasedDispatcher<T>() {
    override var handler = Handler(Looper.getMainLooper(), { internalHandleMessage(it) })
}