package com.mrlukashem.growingcircles.animations

import android.arch.lifecycle.MutableLiveData
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF

import com.mrlukashem.growingcircles.views.SpaceManager
import com.mrlukashem.growingcircles.views.size


class CurrentScoreAnimation(position: Position,
                            spaceManager: SpaceManager,
                            currentScoreData: MutableLiveData<Int>) : AnimatedComponent() {

    private val paint = Paint()
    override val coordinates: PointF
        get() = PointF(xPosition, yPosition)
    private val xPosition: Float
    private val yPosition: Float
    private var currentScore: Int = 0

    init {
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = spaceManager.deviceDisplay.size().x * 0.07f
        paint.color = Color.BLACK
        val deviceHeight = spaceManager.deviceDisplay.size().y
        val deviceWidth = spaceManager.deviceDisplay.size().x

        xPosition = deviceWidth * 0.5f
        yPosition = deviceHeight * 0.05f

        currentScoreData.observeForever {
            it?.let {
                currentScore = it
            }
        }
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) { }

    override fun draw(canvas: Canvas) {
        canvas.drawText(currentScore.toString(), xPosition, yPosition, paint)
    }
}