package com.mrlukashem.growingcircles.gameobjects

import com.mrlukashem.growingcircles.drawingcomponents.CircleDrawingComponent
import com.mrlukashem.growingcircles.drawingcomponents.DrawingComponent


class CircleObject : GameObject() {
    override val mDrawingComponent: DrawingComponent = CircleDrawingComponent()

    override fun move(x: Double, y: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateSize(percentage: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}