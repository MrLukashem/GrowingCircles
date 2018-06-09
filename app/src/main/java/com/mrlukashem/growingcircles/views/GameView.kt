package com.mrlukashem.growingcircles.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

import com.mrlukashem.growingcircles.FrameDrawnObservable
import com.mrlukashem.growingcircles.FrameDrawnObserver
import com.mrlukashem.growingcircles.drawingcomponents.BaseDrawingComponentFactory
import com.mrlukashem.growingcircles.drawingcomponents.DrawingComponent
import com.mrlukashem.growingcircles.drawingcomponents.DrawingComponentFactory
import com.mrlukashem.growingcircles.gameobjects.GameObject
import com.mrlukashem.growingcircles.gameobjects.GameObjectFactory
import com.mrlukashem.growingcircles.gameobjects.GameObjectFactory.GameObjectType.*
import com.mrlukashem.growingcircles.gameobjects.RandomGameObjectFactory


class GameView(context: Context)
    : View(context), FrameDrawnObservable {

    private val mPaint: Paint
    private val mGameObjects: MutableList<GameObject> = mutableListOf()
    private val mDrawingComponents: MutableList<DrawingComponent> = mutableListOf()
    private val mFrameDrawnObservers: MutableList<FrameDrawnObserver> = mutableListOf()

    private var left = 0f
    private var top = 0f
    private var right = 300f
    private var bottom = 300f

    init {
        mPaint = Paint()
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.RED

        initGameObjects()
    }

    private fun initGameObjects() {
        val circleObjectsFactory: GameObjectFactory = RandomGameObjectFactory()
        val gameObject: GameObject = circleObjectsFactory.create(CIRCLE_OBJECT)

        val drawingComponentsFactory: DrawingComponentFactory = BaseDrawingComponentFactory()
        val drawingComponent: DrawingComponent = drawingComponentsFactory.create(gameObject)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        right +=1
        bottom +=1
        canvas?.drawOval(left, top, right, bottom, mPaint)

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
}