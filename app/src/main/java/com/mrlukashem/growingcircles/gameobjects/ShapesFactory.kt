package com.mrlukashem.growingcircles.gameobjects


interface ShapesFactory {
    fun create(shapeType: ShapeType): Shape

    enum class ShapeType {
        CIRCLE_OBJECT,
    }
}