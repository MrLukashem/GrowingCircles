package com.mrlukashem.growingcircles.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity(), ViewOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        GameObserver(applicationContext, lifecycle, this)
    }

    override fun addView(view: View) {
        setContentView(view)
    }
}
