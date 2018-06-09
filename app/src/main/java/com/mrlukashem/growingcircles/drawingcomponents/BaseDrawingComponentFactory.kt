package com.mrlukashem.growingcircles.drawingcomponents

import com.mrlukashem.growingcircles.gameobjects.CircleObject
import com.mrlukashem.growingcircles.gameobjects.GameObject

class BaseDrawingComponentFactory : DrawingComponentFactory {

    override fun create(gameObject: GameObject): DrawingComponent {
        return when (gameObject) {
            is CircleObject -> makeCircleDrawingComponent(gameObject)
            else -> EmptyDrawingComponent()
        }
    }

    private fun makeCircleDrawingComponent(circleObject: CircleObject): DrawingComponent {
        return CircleDrawingComponent(circleObject)
    }
}