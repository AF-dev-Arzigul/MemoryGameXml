package uz.gita.memorygameYB.domain

import android.content.Context
import android.content.SharedPreferences
import uz.gita.memorygameYB.data.models.Level

class LocalData {
    companion object {
        private lateinit var sharedPref: SharedPreferences
        fun getInstance(context: Context): SharedPreferences {
            if (!::sharedPref.isInitialized) sharedPref =
                context.getSharedPreferences("DATA", Context.MODE_PRIVATE)
            return sharedPref
        }

        fun position(level: Level, position: Int) {
            sharedPref.edit().putInt("POSITION${level.toString()}", position).apply()
        }

        fun position(level: Level): Int {
            return sharedPref.getInt("POSITION${level.toString()}", 1)
        }

        fun sound(status: Boolean) {
            sharedPref.edit().putBoolean("SOUND", status).apply()
        }

        fun sound(): Boolean {
            return sharedPref.getBoolean("SOUND", true)
        }

        fun diamond(diamond: Int) {
            sharedPref.edit().putInt("DIAMOND", diamond).apply()
        }

        fun diamond(): Int {
            return sharedPref.getInt("DIAMOND", 10)
        }

        fun time(time: Int) {
            sharedPref.edit().putInt("TIME", time).apply()
        }

        fun time(): Int {
            return sharedPref.getInt("TIME", 0)
        }
    }
}