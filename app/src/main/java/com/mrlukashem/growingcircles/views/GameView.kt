package com.mrlukashem.growingcircles.views

import android.content.Context
import android.graphics.*
import android.view.View

import com.mrlukashem.growingcircles.SpaceConverter
import com.mrlukashem.growingcircles.animations.Drawable
import com.mrlukashem.growingcircles.size


class GameView(context: Context)
    : View(context), FrameDrawnObservable {

    private val tag = "GameView"
    private val drawableComposite = DrawableComposite()
    private val mFrameDrawnObservers: MutableList<FrameDrawnObserver> = mutableListOf()

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
}
