package com.mrlukashem.growingcircles.views

import android.content.Context
import android.graphics.*
import android.view.View

import com.mrlukashem.growingcircles.animations.Drawable


class GameView(context: Context)
    : View(context), FrameDrawnObservable {

    private val tag = "GameView"

    private val drawables: MutableList<Drawable> = mutableListOf()
    private val mFrameDrawnObservers: MutableList<FrameDrawnObserver> = mutableListOf()

    init {
//        val shader = RadialGradient(150f, 150f, 150f, intArrayOf(Color.BLUE, Color.BLACK), floatArrayOf(0.9f, 1.0f), Shader.TileMode.REPEAT)
//        mPaint.shader= shader
//        mPaint.strokeWidth = 10f

//        mPaint.setShadowLayer(50f, 5f, 5f, Color.BLACK)
//        setLayerType(LAYER_TYPE_SOFTWARE, mPaint)
    }

    fun addDrawable(drawable: Drawable) {
        drawables.add(drawable)
    }

    fun removeDrawable(drawable: Drawable) {
        drawables.remove(drawable)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawables.forEach {
            val drawable = it
            canvas?.let {
                drawable.draw(canvas)
            }
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
