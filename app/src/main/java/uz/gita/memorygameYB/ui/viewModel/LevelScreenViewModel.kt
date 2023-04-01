package uz.gita.memorygameYB.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.memorygameYB.data.models.Level


interface LevelScreenViewModel {
    val openGameScreenLiveData: LiveData<Level>

    fun openGameScreen(level: Level)
}