package com.mrlukashem.growingcircles.views

import android.content.Context
import android.graphics.*
import android.view.View
import com.mrlukashem.growingcircles.Observers.FrameDrawnObservable
import com.mrlukashem.growingcircles.Observers.FrameDrawnObserver

import com.mrlukashem.growingcircles.drawable.Drawable


class GameView(context: Context)
    : View(context), FrameDrawnObservable, DrawingComponent {

    private val tag = "GameView"
    private val drawableComposite = DrawableComposite()
    private val mFrameDrawnObservers: MutableSet<FrameDrawnObserver> = mutableSetOf()

    init {
        setBackgroundColor(Color.rgb(237, 231, 246))
    }

    fun addDrawable(drawable: Drawable) {
        drawableComposite.add(drawable)
    }

    fun removeDrawable(drawable: Drawable) {
        drawableComposite.remove(drawable)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            drawableComposite.draw(it)
        }

        notifyObservers()
    }

    private fun notifyObservers() {
        // Log.e(tag, "notifyObserves")

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
