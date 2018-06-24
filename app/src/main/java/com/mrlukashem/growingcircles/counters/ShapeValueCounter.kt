package com.mrlukashem.growingcircles.counters

import android.graphics.PointF

import com.mrlukashem.growingcircles.gameobjects.Shape


class ShapeValueCounter : ValueCounter<Shape> {

    override fun calculate(obj: Shape): Int {
        return (obj.boundsRadius * 0.4f).toInt()
    }
}