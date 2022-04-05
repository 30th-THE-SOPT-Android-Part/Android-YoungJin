package org.sopt.soptseminar

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SoptSeminarApplication  : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}