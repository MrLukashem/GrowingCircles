package com.mrlukashem.growingcircles

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.graphics.Color
import android.view.Choreographer

import com.mrlukashem.growingcircles.animations.DynamicCircleShapeAnimation
import com.mrlukashem.growingcircles.gameobjects.ShapesFactory
import com.mrlukashem.growingcircles.gameobjects.GrowingCircleShape
import com.mrlukashem.growingcircles.gameobjects.RandomShapesFactory
import com.mrlukashem.growingcircles.gameobjects.Shape
import com.mrlukashem.growingcircles.views.FrameDrawnObserver
import com.mrlukashem.growingcircles.views.GameView
import com.mrlukashem.mediacontentprovider.multithreading.TasksDispatcher


class GameObserver(
        private val context: Context,
        private val lifeCycle: Lifecycle,
        private val viewOwner: ViewOwner)
    : LifecycleObserver, Choreographer.FrameCallback, FrameDrawnObserver, OnFrameObservable {

    private lateinit var mChoreographer: Choreographer
    private val dispatcher = TasksDispatcher<Unit>()
    private val spaceConverter = SpaceConverter(context)
    private var gameView: GameView = GameView(context)

    private val onFrameObservers: MutableList<OnFrameObserver> = mutableListOf()
    private val animationsComponents: MutableList<AnimationComponent> = mutableListOf()
    private val games: MutableList<Shape> = mutableListOf()

    private var lastFrameTimeMillis: Long = 0

    init {
        lifeCycle.addObserver(this)
        gameView.registerObserver(this)

        viewOwner.addView(gameView)

        dispatcher.begin()
        dispatcher.dispatch({
            mChoreographer = Choreographer.getInstance()
        }, {
            mChoreographer.postFrameCallback(this)
        })


        initGameComponents()
    }

    override fun registerObserver(observer: OnFrameObserver) {
        onFrameObservers.add(observer)
    }

    override fun removeObserver(observer: OnFrameObserver) {
        onFrameObservers.remove(observer)
    }

    private fun initGameComponents() {
        val shapesFactory = RandomShapesFactory(games, spaceConverter.gameDisplay)

        val shape = GrowingCircleShape(300f, 400f, 100f)

        val randomShape = shapesFactory.create(ShapesFactory.ShapeType.CIRCLE_OBJECT)

        games.add(shape)
        games.add(randomShape)

        gameView.addDrawable(animation)
        gameView.addDrawable(animation2)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        viewOwner.addView(gameView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifeCycle.removeObserver(this)
        dispatcher.quit()
    }

    override fun doFrame(frameTimeNanos: Long) {
        val frameTimeMillis = frameTimeNanos.toMillis()
        val deltaTimeMillis = delta(frameTimeMillis)
        lastFrameTimeMillis = frameTimeMillis

        animationsComponents.forEach {
            it.onFrameOccurred(frameTimeMillis, deltaTimeMillis)
        }
        games.forEach {
            it.onFrameOccurred(frameTimeMillis, deltaTimeMillis)
        }

        gameView.postInvalidate()
    }

    private fun Long.toMillis(): Long = this / 1000000

    private fun delta(frameTimeNanos: Long): Long = ((frameTimeNanos - lastFrameTimeMillis))

    override fun onFrameDrawn() {
        mChoreographer.postFrameCallback(this)
    }
}