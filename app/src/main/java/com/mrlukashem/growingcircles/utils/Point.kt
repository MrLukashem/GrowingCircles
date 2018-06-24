package com.mrlukashem.growingcircles.utils

import android.graphics.Point
import android.graphics.PointF

import kotlin.math.pow


fun PointF.distanceTo(point: PointF): Float {
    return ((point.x - x).pow(2) + (point.y - y).pow(2)).pow(.5f)
}

fun Point.distanceTo(point: Point): Int {
    return ((point.x - x).toFloat().pow(2) + (point.y - y).toFloat().pow(2)).pow(.5f).toInt()
}

fun PointF.toVectorWith(point: PointF): Vector2D = Vector2D(point.x - x, point.y - y)

fun PointF.midPoint(point: PointF): PointF = PointF(((x + point.x) / 2f), ((y + point.y) / 2f))
