package com.mrlukashem.growingcircles.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.graphics.PointF
import android.util.Log
import android.view.Choreographer
import android.view.MotionEvent
import android.view.View
import com.mrlukashem.growingcircles.Observers.*
import com.mrlukashem.growingcircles.views.SpaceManager
import com.mrlukashem.growingcircles.animations.AnimatedComponent
import com.mrlukashem.growingcircles.animations.CurrentScoreAnimation
import com.mrlukashem.growingcircles.animations.MovingShapeAnimation
import com.mrlukashem.growingcircles.animations.Position
import com.mrlukashem.growingcircles.counters.ShapeValueCounter
import com.mrlukashem.growingcircles.counters.ValueCounter
import com.mrlukashem.growingcircles.drawable.DrawableShape
import com.mrlukashem.growingcircles.gameobjects.*

import com.mrlukashem.growingcircles.views.GameView
import com.mrlukashem.mediacontentprovider.multithreading.TasksDispatcher


class GameObserver(
        context: Context,
        private val lifeCycle: Lifecycle,
        private val viewOwner: ViewOwner,
        private val onCollisionStrategy: (
                collidedShapes: Pair<DrawableShape, DrawableShape>,
                shapesController: DrawableShapesController,
                valueCounter: ValueCounter<Shape>) -> Unit)
    : LifecycleObserver, Choreographer.FrameCallback, FrameDrawnObserver,
        CollisionObserver<DrawableShape>, OnFrameObservable, GameSupervisor {

    private val onFrameObservers: MutableList<OnFrameObserver> = mutableListOf()
    private val drawableShapes: MutableList<DrawableShape> = mutableListOf()

    private lateinit var mChoreographer: Choreographer
    private val dispatcher = TasksDispatcher<Unit>()
    private val spaceManager = SpaceManager(context)
    private val collisionObservable: CollisionObservable<DrawableShape> =
            ShapesCollisionObservable(drawableShapes)
    private val gameView: GameView = GameView(context)
    private lateinit var drawableShapesController: DrawableShapesController
    private val shapeValueCounter: ValueCounter<Shape> = ShapeValueCounter()
    private lateinit var currentScoreAnimation: AnimatedComponent

    private val currentScore: MutableLiveData<Int> = MutableLiveData()
    private var lastFrameTimeMillis: Long = 0

    init {
        startDispatcher()
        registerToObservables()

        prepareShapesController()
        prepareGameView()
        prepareAnimations()
        prepareOnFrameObservers()
        prepareGameObjectsStorage()

        startFrameCallbacks()
    }

    private fun startDispatcher() {
        dispatcher.begin()
    }

    private fun registerToObservables() {
        lifeCycle.addObserver(this)
        collisionObservable.registerObserver(this)
    }

    private fun prepareShapesController() {
        drawableShapesController = DrawableShapesController(this, spaceManager)
    }

    private fun prepareGameView() {
        viewOwner.addView(gameView)

        gameView.registerObserver(this)
        gameView.setOnTouchListener(::onTouchEvent)
    }

    private fun prepareAnimations() {
        currentScoreAnimation = CurrentScoreAnimation(Position.TOP_RIGHT,
                spaceManager, currentScore)
        gameView.addDrawable(currentScoreAnimation)
    }

    private fun prepareOnFrameObservers() {
        registerObserver(collisionObservable)
    }

    private fun prepareGameObjectsStorage() {
        drawableShapesController.registerOnDestroyCallback {
            gameView.removeDrawable(it)
            drawableShapes.remove(it)

            true
        }
        drawableShapesController.registerOnCreateCallback {
            val decoratedDrawableShape = RadialShadowDecorator(
                    DrawableShapeCounterDecorator(shapeValueCounter, it))
            drawableShapes.add(decoratedDrawableShape)
            gameView.addDrawable(decoratedDrawableShape)

            decoratedDrawableShape
        }

        drawableShapesController.createFewShapes(10,
                DrawableShapesFactory.ShapeType.CIRCLE_OBJECT)
    }

    private fun startFrameCallbacks() {
        dispatcher.dispatch({
            mChoreographer = Choreographer.getInstance()
        }, {
            mChoreographer.postFrameCallback(this)
        })
    }

    override fun registerObserver(observer: OnFrameObserver) {
        dispatcher.dispatch({
            onFrameObservers.add(observer)
        }, {})
    }

    override fun removeObserver(observer: OnFrameObserver) {
        dispatcher.dispatch({
            onFrameObservers.remove(observer)
        }, {})
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

    override fun onCollision(firstObj: DrawableShape, secondObj: DrawableShape) {
        Log.i(GameObserver::class.java.name, "Collision detected")
        if (isNotSameKind(firstObj, secondObj)) {
            updateCurrentScore(firstObj)
            updateCurrentScore(secondObj)
        }

        drawableShapesController.destroyShape(firstObj)
        drawableShapesController.destroyShape(secondObj)

        dispatcher.dispatch({
            onCollisionStrategy.invoke(
                    Pair(firstObj, secondObj), drawableShapesController, shapeValueCounter)
        }, {})
    }

    private fun isNotSameKind(firstObj: DrawableShape, secondObj: DrawableShape): Boolean {
        return firstObj.paint.color != secondObj.paint.color
    }

    private fun updateCurrentScore(vararg shapeObjects: DrawableShape) {
        shapeObjects.forEach {
            val score: Int = if (currentScore.value != null) currentScore.value!! else 0
            currentScore.postValue(score + shapeValueCounter.calculate(it))
        }
    }

    private fun onTouchEvent(view: View?, motionEvent: MotionEvent?): Boolean {
        motionEvent?.let {
            drawableShapes.filter {
                it.contains(motionEvent.x, motionEvent.y)
            }.forEach {
                dispatcher.dispatch({
                    drawableShapesController.destroyShape(it)
                }, {})
            }
        }

        return true
    }

    override fun onFrameDrawn() {
        mChoreographer.postFrameCallback(this)
    }

    override fun start(gameOptions: Map<String, String>) {
        val runTime = gameOptions["time"]

    }

    override fun reset() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}