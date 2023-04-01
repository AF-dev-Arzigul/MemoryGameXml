package uz.gita.memorygameYB.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.ui.viewModel.LevelScreenViewModel
import javax.inject.Inject

@HiltViewModel
class LevelScreenViewModelImpl @Inject constructor() : LevelScreenViewModel, ViewModel() {
    override val openGameScreenLiveData: MutableLiveData<Level> = MutableLiveData()

    override fun openGameScreen(level: Level) {
        openGameScreenLiveData.value = level
    }
}