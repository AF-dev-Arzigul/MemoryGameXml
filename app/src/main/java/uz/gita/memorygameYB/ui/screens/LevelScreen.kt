package uz.gita.memorygameYB.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygameYB.MainActivity
import uz.gita.memorygameYB.R
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.databinding.ScreenLevelBinding
import uz.gita.memorygameYB.domain.LocalData
import uz.gita.memorygameYB.ui.viewModel.LevelScreenViewModel
import uz.gita.memorygameYB.ui.viewModel.impl.LevelScreenViewModelImpl

@AndroidEntryPoint
class LevelScreen : Fragment(R.layout.screen_level) {

    private val viewBinding: ScreenLevelBinding by viewBinding(ScreenLevelBinding::bind)
    private val viewModel: LevelScreenViewModel by viewModels<LevelScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openGameScreenLiveData.observe(this@LevelScreen, openGameScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(10000)
            shakeView(viewBinding.btnEasy)
            delay(1000)
            shakeView(viewBinding.btnMedium)
            delay(1000)
            shakeView(viewBinding.btnHard)
        }

        viewBinding.textDiamon.text = LocalData.diamond().toString()

        viewBinding.btnEasy.setOnClickListener { viewModel.openGameScreen(Level.EASY) }
        viewBinding.btnMedium.setOnClickListener { viewModel.openGameScreen(Level.MEDIUM) }
        viewBinding.btnHard.setOnClickListener { viewModel.openGameScreen(Level.HARD) }
        viewBinding.btnExit.setOnClickListener { findNavController().popBackStack() }


    }

    private val openGameScreenObserver = Observer<Level> {
        findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(it))
    }

    fun shakeView(view: View) {
        view.animate()
            .setDuration(50)
            .xBy(8f)
            .withEndAction {
                view.animate()
                    .setDuration(50)
                    .xBy(-16f)
                    .withEndAction {
                        view.animate()
                            .setDuration(50)
                            .xBy(16f)
                            .withEndAction {
                                view.animate()
                                    .setDuration(50)
                                    .xBy(-8f)
                                    .start()
                            }
                            .start()
                    }
                    .start()
            }
            .start()
    }
}