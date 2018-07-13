package com.mrlukashem.growingcircles.views

import android.content.Context
import android.graphics.*
import android.os.Looper
import android.util.Log
import android.view.View
import com.mrlukashem.growingcircles.Observers.FrameDrawnObservable
import com.mrlukashem.growingcircles.Observers.FrameDrawnObserver

import com.mrlukashem.growingcircles.drawable.Drawable
import com.mrlukashem.mediacontentprovider.multithreading.Dispatcher
import com.mrlukashem.mediacontentprovider.multithreading.MainThreadDispatcher
import com.mrlukashem.mediacontentprovider.multithreading.TasksDispatcher


class GameView(context: Context)
    : View(context), FrameDrawnObservable, DrawingComponent {

    private val tag = "GameView"
    private val drawableComposite = DrawableComposite()
    private val mFrameDrawnObservers: MutableSet<FrameDrawnObserver> = mutableSetOf()
    private val mainThreadDispatcher: Dispatcher<Unit> = MainThreadDispatcher()

    init {
        setBackgroundColor(Color.rgb(63, 42, 96))
        mainThreadDispatcher.begin()
    }

    override fun addDrawable(drawable: Drawable) {
        mainThreadDispatcher.dispatch({
            drawableComposite.add(drawable)
        }, {})
    }

    override fun removeDrawable(drawable: Drawable) {
        mainThreadDispatcher.dispatch({
            drawableComposite.remove(drawable)
        }, {})
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            drawableComposite.draw(it)
        }

        notifyObservers()
    }

    private fun notifyObservers() {
        mFrameDrawnObservers.forEach {
            it.onFrameDrawn()
        }
    }

    override fun removeObserver(observer: FrameDrawnObserver) {
        mFrameDrawnObservers.remove(observer)
    }

    override fun registerObserver(observer: FrameDrawnObserver) {
        mFrameDrawnObservers.add(observer)
    }

    override fun performClick(): Boolean = super.performClick()
}
