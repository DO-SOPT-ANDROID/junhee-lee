package org.sopt.dosopttemplate

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import org.sopt.dosopttemplate.data.datasource.local.DoSoptStorage
import timber.log.Timber

@HiltAndroidApp
class DoSoptApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DoSoptStorage.init(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
