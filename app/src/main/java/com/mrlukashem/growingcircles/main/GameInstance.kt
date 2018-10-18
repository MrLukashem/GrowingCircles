package com.mrlukashem.growingcircles.main

import android.os.Bundle

interface GameInstance : GameInstanceObservable {
    fun start(context: Bundle)
    fun quit()
}