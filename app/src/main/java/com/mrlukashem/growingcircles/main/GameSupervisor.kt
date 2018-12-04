package com.mrlukashem.growingcircles.main

import android.content.Context
import android.view.Choreographer

import com.mrlukashem.growingcircles.Observers.OnFrameObservable
import com.mrlukashem.growingcircles.Observers.OnFrameObserver
import com.mrlukashem.growingcircles.views.GameView

class GameSupervisor(context: Context) : GameInstance, OnFrameObservable, Choreographer.FrameCallback {
    private var lastFrameTimeMillis: Long = 0
    private val gameView: GameView = GameView(context)
    private lateinit var drawableShapesController: DrawableShapesController
    private val onFrameObservers: MutableList<OnFrameObserver> = mutableListOf()

    init {
        drawableShapesController.registerOnDestroyCallback {
            gameView.removeDrawable(it)
            true
        }
        drawableShapesController.registerOnCreateCallback {
            gameView.addDrawable(it)
            it
        }
    }

    override fun doFrame(frameTimeNanos: Long) {
        val frameTimeMillis = frameTimeNanos.toMillis()
        val deltaTimeMillis = delta(frameTimeMillis)
        lastFrameTimeMillis = frameTimeMillis

        onFrameOccurredUpdate(frameTimeMillis, deltaTimeMillis)

        gameView.postInvalidate()
    }

    private fun Long.toMillis(): Long = this / 1000000

    private fun delta(frameTimeMillis: Long): Long {
        val dt = (frameTimeMillis - lastFrameTimeMillis)
        return if (dt < frameTimeMillis) {
            dt
        } else {
            0
        }
    }

    private fun onFrameOccurredUpdate(frameTimeMillis: Long, deltaTimeMillis: Long) {
        onFrameObservers.forEach {
            it.onFrameOccurred(frameTimeMillis, deltaTimeMillis)
        }
    }

    override fun registerObserver(observer: OnFrameObserver) {
        onFrameObservers.add(observer)
    }

    override fun removeObserver(observer: OnFrameObserver) {
        onFrameObservers.remove(observer)
    }

    override fun start(gameScenarios: List<GameScenario>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
