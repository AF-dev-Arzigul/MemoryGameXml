package uz.gita.memorygameYB.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.memorygameYB.domain.LocalData

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalData.getInstance(this)
//        LocalData.diamond(1000)
    }
}