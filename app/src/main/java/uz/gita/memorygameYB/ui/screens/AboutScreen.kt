package uz.gita.memorygameYB.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.memorygameYB.R

class AboutScreen : Fragment(R.layout.screen_about) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<ImageView>(R.id.btn_back)
            .setOnClickListener { findNavController().popBackStack() }
    }
}