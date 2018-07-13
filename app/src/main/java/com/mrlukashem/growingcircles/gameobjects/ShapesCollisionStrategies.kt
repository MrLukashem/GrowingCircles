package com.mrlukashem.growingcircles.gameobjects

import android.view.Display
import com.mrlukashem.growingcircles.utils.distanceTo
import kotlin.math.abs
import kotlin.math.pow


fun defaultCircleBasedCollisionStrategy(firstShape: Shape, secondShape: Shape): Boolean {
    val shapesDistance = firstShape.position.distanceTo(secondShape.position)

    return shapesDistance <= secondShape.boundsRadius + firstShape.boundsRadius
}

interface CollisionStrategy {
    fun collisionOccurred(firstShape: Shape, secondShape: Shape): Boolean
}

class ScreenBoundsCollisionStrategy(
        private val shapesCollisionStrategy: (Shape, Shape) -> Boolean =
                ::defaultCircleBasedCollisionStrategy, private val display: Display)
    : CollisionStrategy {

    override fun collisionOccurred(firstShape: Shape, secondShape: Shape): Boolean {
        return shapesCollisionStrategy.invoke(firstShape, secondShape)
                || screenBoundsCollisionOccurred(firstShape, secondShape)
    }

    private fun screenBoundsCollisionOccurred(firstShape: Shape, secondShape: Shape): Boolean {
        return false
    }
}
