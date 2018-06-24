package com.mrlukashem.growingcircles.gameobjects

import com.mrlukashem.growingcircles.drawable.DrawableShape


interface DrawableShapesFactory {
    fun create(shapeType: ShapeType): DrawableShape

    enum class ShapeType {
        CIRCLE_OBJECT,
    }
}
