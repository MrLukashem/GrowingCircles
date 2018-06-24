package com.mrlukashem.growingcircles.gameobjects

import kotlin.math.abs
import kotlin.math.pow


fun defaultCircleBasedCollisionStrategy(firstShape: Shape, secondShape: Shape): Boolean {
    val d = ((secondShape.position.x - firstShape.position.x).pow(2) + (secondShape.position.y - firstShape.position.y).pow(2)).pow(0.5f)

    return abs(secondShape.boundsRadius - firstShape.boundsRadius) <= d && d <= secondShape.boundsRadius + firstShape.boundsRadius
}