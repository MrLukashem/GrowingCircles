package com.mrlukashem.growingcircles.utils

import android.graphics.RectF

import com.mrlukashem.growingcircles.gameobjects.Shape
import com.mrlukashem.growingcircles.gameobjects.toDisplaySpace


fun Shape.toRectF(): RectF {
    val convertedShape = toDisplaySpace()

    return RectF(
            convertedShape.position.x - boundsRadius,
            convertedShape.position.y - boundsRadius,
            convertedShape.position.x + boundsRadius,
            convertedShape.position.y + boundsRadius)
}

fun Shape.toRectF(xPosition: Float, yPosition: Float, radius: Float): RectF {
    val convertedShape = toDisplaySpace()

    return RectF(
            convertedShape.position.x - radius,
            convertedShape.position.y - radius,
            convertedShape.position.x + radius,
            convertedShape.position.y + radius)
}