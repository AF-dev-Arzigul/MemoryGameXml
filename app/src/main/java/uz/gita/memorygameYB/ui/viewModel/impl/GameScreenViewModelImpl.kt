package uz.gita.memorygameYB.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.domain.usecase.GameUseCase
import uz.gita.memorygameYB.ui.viewModel.GameScreenViewModel
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModelImpl @Inject constructor(private val useCase: GameUseCase) :
    GameScreenViewModel, ViewModel() {
    override val gameModelLiveData: MutableLiveData<List<GameModel>> = MutableLiveData()

    override fun getDataByLevel(level: Level) {
        useCase.getDataByLevel(level)
            .onEach {
                gameModelLiveData.postValue(it)
            }.launchIn(viewModelScope)
    }

    override fun btnClicked(state: Boolean, position: Int) {
        TODO("Not yet implemented")
    }
}