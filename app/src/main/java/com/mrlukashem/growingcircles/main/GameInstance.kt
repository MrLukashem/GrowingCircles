package com.mrlukashem.growingcircles.main

interface GameInstance {
    fun start(gameScenarios: List<GameScenario>)
    fun stop()
}