package uz.gita.memorygameYB.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val backLiveData = MutableLiveData<Unit>()

    fun triggerBack() {
        backLiveData.value = Unit
    }
}