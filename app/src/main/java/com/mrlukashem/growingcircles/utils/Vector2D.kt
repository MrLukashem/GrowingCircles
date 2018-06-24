package com.mrlukashem.growingcircles.utils

import kotlin.math.acos
import kotlin.math.pow


class Vector2D(var x: Float, var y: Float) {

    fun length(): Float = (x.pow(2f) + y.pow(2f)).pow(.5f)
}

fun Vector2D.scalarProduct(vec: Vector2D): Float = x * vec.x + y * vec.y

fun Vector2D.vectorsAngle(vec: Vector2D): Float {
    return acos(this.scalarProduct(vec) / (length() * vec.length()))
}