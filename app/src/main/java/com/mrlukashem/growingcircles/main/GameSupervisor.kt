package com.mrlukashem.growingcircles.main

interface GameSupervisor {
    fun begin(gameOptions: Map<String, String>)
    fun quit()
}
