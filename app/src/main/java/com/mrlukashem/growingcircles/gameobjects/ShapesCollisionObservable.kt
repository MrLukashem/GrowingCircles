package com.mrlukashem.growingcircles.gameobjects

import com.mrlukashem.growingcircles.Observers.CollisionObservable
import com.mrlukashem.growingcircles.Observers.CollisionObserver
import com.mrlukashem.growingcircles.drawable.DrawableShape


class ShapesCollisionObservable(private val drawableShapes: List<DrawableShape>)
    : CollisionObservable<DrawableShape> {

    private val observers: MutableSet<CollisionObserver<DrawableShape>> = mutableSetOf()

    override fun registerObserver(observer: CollisionObserver<DrawableShape>) {
        observers.add(observer)
    }

    override fun unregisterObserver(observer: CollisionObserver<DrawableShape>) {
        observers.remove(observer)
    }

    override fun onFrameOccurred(frameTimeMillis: Long, deltaTimeMillis: Long) {
        checkShapesCollisions()
    }

    private fun checkShapesCollisions() {
        val detectedCollisionPairs: MutableList<Pair<DrawableShape, DrawableShape>> = mutableListOf()
        for (i in 0 until drawableShapes.size) {
            for (j in i+1 until drawableShapes.size) {
                val firstShape = drawableShapes[i]
                val secondShape = drawableShapes[j]

                if (firstShape.hasCollisionWith(secondShape)) {
                    detectedCollisionPairs.add(Pair(drawableShapes[i], drawableShapes[j]))
                }
            }
        }

        detectedCollisionPairs.forEach {
            val collisionPair = it
            observers.forEach {
                it.onCollision(collisionPair.first, collisionPair.second)
            }
        }
    }
}