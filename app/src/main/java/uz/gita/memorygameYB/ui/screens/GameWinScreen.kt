package uz.gita.memorygameYB.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameYB.R
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.databinding.ScreenGameWinBinding
import uz.gita.memorygameYB.domain.LocalData

@AndroidEntryPoint
class GameWinScreen : Fragment(R.layout.screen_game_win) {

    private val viewBinding: ScreenGameWinBinding by viewBinding(ScreenGameWinBinding::bind)
    private val navigation by lazy { findNavController() }
    private val args: GameWinScreenArgs by navArgs()

    lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        level = args.levelToGWS
        LocalData.position(level, LocalData.position(level) + 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LocalData.diamond(LocalData.diamond() + 10)

        viewBinding.btnNext.setOnClickListener{
            navigation.navigate(GameWinScreenDirections.actionGameWinScreenToLevelScreen())
        }
        viewBinding.btnLevel.setOnClickListener{
            navigation.navigate(GameWinScreenDirections.actionGameWinScreenToGameScreen(level))
        }
    }
}