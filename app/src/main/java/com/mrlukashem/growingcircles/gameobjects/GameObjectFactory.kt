package com.mrlukashem.growingcircles.gameobjects


interface GameObjectFactory {
    fun create(gameObjectType: GameObjectType): ShapeObject

    enum class GameObjectType {
        CIRCLE_OBJECT,
    }
}