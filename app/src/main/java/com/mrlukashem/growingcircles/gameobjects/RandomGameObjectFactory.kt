package com.mrlukashem.growingcircles.gameobjects

import android.graphics.Point
import android.view.Display

import java.util.*


class RandomGameObjectFactory(existingObjects: List<ShapeObject>, gameDisplay: Display)
    : GameObjectFactory {

    private val existingObjects: MutableList<ShapeObject> = mutableListOf()
    private val random: Random = Random()
    private val gameDisplay: Display = gameDisplay

    init {
        this.existingObjects.addAll(existingObjects)
    }

    override fun create(gameObjectType: GameObjectFactory.GameObjectType): ShapeObject {
        return when (gameObjectType) {
            GameObjectFactory.GameObjectType.CIRCLE_OBJECT -> makeCircleObject()
        }
    }

    private fun makeCircleObject(): ShapeObject {
        val xPosition = random.nextInt(0..width())
        val yPosition = random.nextInt(0..height())
        val radius = random.nextInt(10..50)

        val circleObject = GrowingShapeObject(xPosition.toFloat(), yPosition.toFloat(),
                radius.toFloat())
        existingObjects.add(circleObject)

        return circleObject
    }

    private fun Random.nextInt(intRange: IntRange): Int {
        return (Random().nextInt() % intRange.last) + intRange.start
    }
    
    private fun size(): Point {
        val size = Point()
        gameDisplay.getSize(size)

        return size
    }

    private fun height() = size().y

    private fun width() = size().x
}