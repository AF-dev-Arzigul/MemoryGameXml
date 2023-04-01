package uz.gita.memorygameYB.ui.screens

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.memorygameYB.R
import uz.gita.memorygameYB.databinding.HomeScreenBinding
import uz.gita.memorygameYB.ui.viewModel.impl.HomeViewModel

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    private val viewBinding: HomeScreenBinding by viewBinding(HomeScreenBinding::bind)
    private val homeViewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.backLiveData.observe(this){
            findNavController().navigateUp()
            requireActivity().finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnPlay.setOnClickListener { findNavController().navigate(HomeScreenDirections.actionHomeScreenToLevelScreen()) }
        viewBinding.btnAbout.setOnClickListener { findNavController().navigate(HomeScreenDirections.actionHomeScreenToAboutScreen())}
        viewBinding.btnExit.setOnClickListener { requireActivity().finish()}

    }
}