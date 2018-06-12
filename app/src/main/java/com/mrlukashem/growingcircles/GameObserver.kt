package com.mrlukashem.growingcircles

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.graphics.Color
import android.view.Choreographer

import com.mrlukashem.growingcircles.animations.AnimationComponent
import com.mrlukashem.growingcircles.animations.CircleShapeAnimation
import com.mrlukashem.growingcircles.gameobjects.GameObjectFactory
import com.mrlukashem.growingcircles.gameobjects.GrowingShapeObject
import com.mrlukashem.growingcircles.gameobjects.RandomGameObjectFactory
import com.mrlukashem.growingcircles.gameobjects.ShapeObject
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
    private val gameObjects: MutableList<ShapeObject> = mutableListOf()

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
        val shapesFactory = RandomGameObjectFactory(gameObjects, spaceConverter.gameDisplay)

        val shape = GrowingShapeObject(300f, 400f, 100f)

        val randomShape = shapesFactory.create(GameObjectFactory.GameObjectType.CIRCLE_OBJECT)

        gameObjects.add(shape)
        gameObjects.add(randomShape)

        val animation = CircleShapeAnimation(Color.rgb(	92,	107,	192))
        animation.attachTo(shape)
        val animation2 = CircleShapeAnimation(Color.rgb(	92,	107,	192))
        animation2.attachTo(randomShape)

        animationsComponents.add(animation)
        animationsComponents.add(animation2)
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
        gameObjects.forEach {
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