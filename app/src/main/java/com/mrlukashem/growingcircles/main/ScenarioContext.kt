package com.mrlukashem.growingcircles.main

import com.mrlukashem.growingcircles.drawable.DrawableShape
import com.mrlukashem.growingcircles.counters.ValueCounter
import com.mrlukashem.growingcircles.Observers.CollisionObservable
import com.mrlukashem.growingcircles.gameobjects.Shape

abstract class ScenarioContext {
    abstract val drawableShapesController: DrawableShapesController
    abstract val shapeValueCounter: ValueCounter<Shape>
    abstract val collisionObservable: CollisionObservable<DrawableShape>
}