package com.mrlukashem.growingcircles.main

import android.os.Bundle

class GameSupervisor : GameInstance {

    private val observers: MutableList<GameInstanceObserver> = mutableListOf()

    private lateinit var gameOberver: GameObserver

    override fun start(context: Bundle) {
        informObserversOnStart()
    }

    override fun quit() {
        informObserversOnQuit()
    }

    override fun register(observer: GameInstanceObserver) {
        observers.add(observer)
    }

    override fun unregister(observer: GameInstanceObserver) {
        observers.remove(observer)
    }

    private fun informObserversOnStart() {
        observers.forEach {
            it.onStart()
        }
    }

    private fun informObserversOnQuit() {
        observers.forEach {
            it.onQuit()
        }
    }
}
