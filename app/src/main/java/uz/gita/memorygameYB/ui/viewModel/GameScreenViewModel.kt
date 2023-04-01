package uz.gita.memorygameYB.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level

interface GameScreenViewModel {
    val gameModelLiveData: LiveData<List<GameModel>>

    fun getDataByLevel(level: Level)

    fun btnClicked(state: Boolean, position: Int)
}