package uz.gita.memorygameYB.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygameYB.R
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.databinding.ScreenGameOverBinding
import uz.gita.memorygameYB.ui.viewModel.GameScreenViewModel
import uz.gita.memorygameYB.ui.viewModel.impl.GameScreenViewModelImpl

@AndroidEntryPoint
class GameOverScreen : Fragment(R.layout.screen_game_over) {

    private val viewBinding: ScreenGameOverBinding by viewBinding(ScreenGameOverBinding::bind)
    private val navigation by lazy { findNavController() }

    private val args: GameOverScreenArgs by navArgs()

    lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.levelToGOS.apply {
            level = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(5000)
            shakeView(viewBinding.btnHome)
            shakeView(viewBinding.btnTry)
        }
        viewBinding.btnHome.setOnClickListener {
            navigation.navigate(GameOverScreenDirections.actionGameOverScreenToLevelScreen())
        }

        viewBinding.btnTry.setOnClickListener {
            navigation.navigate(GameOverScreenDirections.actionGameOverScreenToGameScreen(level))
        }
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