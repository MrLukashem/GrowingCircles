package com.mrlukashem.growingcircles.animations

import android.graphics.PointF

import com.mrlukashem.growingcircles.Observers.OnFrameObservable
import com.mrlukashem.growingcircles.Observers.OnFrameObserver
import com.mrlukashem.growingcircles.drawable.Drawable
import com.mrlukashem.growingcircles.views.DrawingComponent


abstract class AnimatedComponent : Drawable, OnFrameObserver {

    abstract val coordinates: PointF

    private lateinit var onFrameObservable: OnFrameObservable
    private lateinit var drawingComponent: DrawingComponent

    fun start(onFrameObservable: OnFrameObservable, drawingComponent: DrawingComponent) {
        this.onFrameObservable = onFrameObservable
        this.drawingComponent = drawingComponent

        this.drawingComponent.addDrawable(this)
        this.onFrameObservable.registerObserver(this)
    }

    fun forceStop() {
        onAnimationCompleted()
    }

    protected fun onAnimationCompleted() {
        onFrameObservable.removeObserver(this)
        drawingComponent.removeDrawable(this)
    }
}