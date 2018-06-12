package com.mrlukashem.growingcircles.gameobjects

import android.view.Display
import com.mrlukashem.growingcircles.size

import java.util.*

import kotlin.math.abs


class RandomGameObjectFactory(existingObjects: List<ShapeObject>, private val gameDisplay: Display)
    : GameObjectFactory {

    private val existingObjects: MutableList<ShapeObject> = mutableListOf()
    private val random: Random = Random()

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
        return abs((nextInt() % intRange.last) + intRange.start)
    }

    private fun height() = gameDisplay.size().y

    private fun width() = gameDisplay.size().x
}