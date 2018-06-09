package com.mrlukashem.growingcircles.gameobjects


interface GameObjectFactory {
    fun create(gameObjectType: GameObjectType): GameObject

    enum class GameObjectType {
        CIRCLE_OBJECT,
    }
}