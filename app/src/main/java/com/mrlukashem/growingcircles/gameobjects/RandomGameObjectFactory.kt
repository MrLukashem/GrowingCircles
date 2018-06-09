package com.mrlukashem.growingcircles.gameobjects


class RandomGameObjectFactory(existingObjects: List<GameObject>) : GameObjectFactory {

    private val mExistingObjects: MutableList<GameObject> = mutableListOf()

    init {
        mExistingObjects.addAll(existingObjects)
    }

    override fun create(gameObjectType: GameObjectFactory.GameObjectType): GameObject {
        return when (gameObjectType) {
            GameObjectFactory.GameObjectType.CIRCLE_OBJECT -> makeCircleObject()
        }
    }

    private fun makeCircleObject(): GameObject {
        val circleObject = CircleObject()
        mExistingObjects.add(circleObject)

        return circleObject
    }
}