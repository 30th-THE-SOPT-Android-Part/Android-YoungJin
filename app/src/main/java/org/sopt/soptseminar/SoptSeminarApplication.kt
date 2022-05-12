package org.sopt.soptseminar

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.soptseminar.modules.datastore.UserPreferenceManager
import javax.inject.Inject

@HiltAndroidApp
class SoptSeminarApplication : Application() {
    @Inject
    lateinit var userPreferenceManager: UserPreferenceManager
    override fun onCreate() {
        super.onCreate()
    }
}