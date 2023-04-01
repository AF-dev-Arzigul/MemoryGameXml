package uz.gita.memorygameYB.ui.screens

import android.content.Context
import android.os.*
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.core.view.allViews
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygameYB.R
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.databinding.ScreenGameBinding
import uz.gita.memorygameYB.domain.LocalData
import uz.gita.memorygameYB.ui.viewModel.GameScreenViewModel
import uz.gita.memorygameYB.ui.viewModel.impl.GameScreenViewModelImpl
import uz.gita.memorygameYB.utils.ExplosionField

@AndroidEntryPoint
class GameScreen : Fragment(R.layout.screen_game) {
    private val viewBinding: ScreenGameBinding by viewBinding(ScreenGameBinding::bind)
    private val viewModel: GameScreenViewModel by viewModels<GameScreenViewModelImpl>()
    private val navigation by lazy { findNavController() }

    private var level = Level.EASY
    private var _width = 0
    private var _height = 0
    private var count = 0
    private var cliCount = 0
    private var countWin = 0
    private var list = ArrayList<CardView>()
    private lateinit var images: ArrayList<CardView>
    private val args: GameScreenArgs by navArgs()
    private lateinit var time: CountDownTimer
    private var imagesView = arrayListOf<ImageView>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.level.apply {
            level = this
            count = x * y
        }

        images = ArrayList(count)

        viewBinding.textDiamon.text = LocalData.diamond().toString()
        viewBinding.cardView.text = "LEVEL ${LocalData.position(level)}"

        viewBinding.containerMain.post {
            Log.d("AAAA", "${viewBinding.containerMain.x} = total x")
            Log.d("AAAA", "${viewBinding.containerMain.y} = total y")
            Log.d("AAAA", "${(viewBinding.containerMain.x / level.x).toInt()}")
            Log.d("AAAA", "${(viewBinding.containerMain.y / level.y).toInt()}")
            _width = (viewBinding.containerImage.width / level.x)
            _height = (viewBinding.containerImage.height / level.y)

//            _width = 180//viewBinding.containerMain.width / level.y
//            _height = 180//viewBinding.containerMain.height / level.x

            viewBinding.containerImage.layoutParams.apply {
                Log.d("AAAA", "${_width * level.x}")
                Log.d("AAAA", "${_height * level.y}")

                width = _width * level.x
                height = _height * level.y
            }
            loadImages()
        }

        viewModel.getDataByLevel(level)
        viewModel.gameModelLiveData.observe(viewLifecycleOwner, gameDataObserver)
        viewBinding.progressBar.max = level.time.toInt()

        //back

        viewBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            time = object : CountDownTimer(level.time, 5) {
                override fun onTick(millisUntilFinished: Long) {
                    viewBinding.progressBar.progress = millisUntilFinished.toInt()
                }

                override fun onFinish() {
                    navigation.navigate(GameScreenDirections.actionGameScreenToGameOverScreen(level))
                }
            }.start()
        }

        viewBinding.btnShowAll.setOnClickListener {
            viewBinding.btnShowAll.isClickable = false
            if (LocalData.diamond() >= 10) {
                LocalData.diamond(LocalData.diamond() - 10)
                viewBinding.textDiamon.text = LocalData.diamond().toString()
                for (i in 0 until images.size) {
                    closeView(images[i])
                    openView(images[i])
                }
                lifecycleScope.launch {
                    delay(level.showTime)
                    for (i in 0 until images.size) {
                        closeView(images[i])
                    }
                    delay(1500)
                    viewBinding.btnShowAll.isClickable = true
                }
            } else {
//                shakeView(viewBinding.icDiamon)
//                shakeView(viewBinding.textDiamon)
                val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            700,
                            VibrationEffect.CONTENTS_FILE_DESCRIPTOR
                        )
                    )
                } else {
                    vibrator.vibrate(700)
                }
                shakeView(viewBinding.cat)

                viewBinding.btnSecond.animate()
                    .setDuration(1500)
                    .yBy(-400f)
                    .withEndAction {
                        viewBinding.btnSecond.animate()
                            .setDuration(1500)
                            .yBy(400f)
                        viewLifecycleOwner.lifecycleScope.launch {
                            delay(2000)
                            viewBinding.btnShowAll.isClickable = true
                        }
                            .start()
                    }
            }
        }
    }

    override fun onPause() {
        time.cancel()
        super.onPause()
    }

    private fun loadImages() {
        for (x in 0 until level.x) {
            for (y in 0 until level.y) {
                val imageView = ImageView(requireContext())
                val cardView = CardView(requireContext())
                viewBinding.containerImage.addView(cardView)
                val lp = cardView.layoutParams as RelativeLayout.LayoutParams

                lp.apply {
                    width = (viewBinding.containerImage.width / level.x) - 5
                    height = (viewBinding.containerImage.height / level.y) - 5

//                    width = 170
//                    height = 170
                }
                cardView.radius = 15f
                cardView.x = x * _width * 1f
                cardView.y = y * _height * 1f
//                imageView.x = x * _width * 1f
//                imageView.y = y * _height * 1f
                lp.setMargins(4, 4, 4, 4)
                cardView.layoutParams = lp
                cardView.elevation = 0f
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.setImageResource(R.drawable.cardd)
                cardView.addView(imageView)
                images.add(cardView)

            }
        }
    }

    private val gameDataObserver = Observer<List<GameModel>> {
        for (i in it.indices) {
            val imageView = images[i]
            imageView.tag = it[i]
            imageView.setOnClickListener {
                if (it.rotationY == 0f) {
                    if (cliCount == 1) {
                        openView(imageView)
                        cliCount++
                        val imageView1 = list[0]
                        if (imageView1.tag == imageView.tag) {
                            lifecycleScope.launch(Dispatchers.Main) {
                                delay(500)
                                val mExplosionField = ExplosionField.attach2Window(activity)
                                mExplosionField.explode(imageView)
                                mExplosionField.explode(imageView1)
                                imageView.visibility = View.INVISIBLE
                                imageView1.visibility = View.INVISIBLE
                                countWin += 2
                                if (countWin == count) {
                                    delay(1000)
                                    navigation.navigate(
                                        GameScreenDirections.actionGameScreenToGameWinScreen(level)
                                    )
                                }
                                list.clear()
                                cliCount = 0
                            }

                        } else {
                            lifecycleScope.launch(Dispatchers.Main) {
                                delay(500)
                                shakeView(imageView)
                                shakeView(imageView1)
                                delay(1000)
                                closeView(imageView)
                                closeView(imageView1)
                                list.clear()
                                cliCount = 0
                            }

                        }
                    } else if (cliCount < 1) {
                        openView(imageView)
                        list.add(imageView)
                        cliCount++
                    }
                }
            }
        }
    }

    private fun closeView(imageView: CardView) {
        imageView.animate()
            .setDuration(200)
            .rotationY(90f)
            .withEndAction {
//                imageView.setImageResource(R.drawable.cardd)
//                imageView.rootView.setBackgroundResource(R.drawable.cardd)
                imageView.children.first {
                    (it as ImageView).setImageResource(R.drawable.cardd)
                    true
                }
                imageView.animate()
                    .setDuration(200)
                    .rotationY(0f)
                    .start()

            }.start()
    }

    private fun openView(imageView: CardView) {
        imageView.animate()
            .setDuration(200)
            .rotationY(90f)
            .withEndAction {
//                imageView.setImageResource((imageView.tag as GameModel).image)
//                imageView[0].setBackgroundResource((imageView.tag as GameModel).image)
                imageView.children.first {
                    (it as ImageView).setImageResource((imageView.tag as GameModel).image)
                    true
//                    (it.tag as GameModel).image
                }
                imageView.animate()
                    .setDuration(200)
                    .rotationY(180f)
                    .setInterpolator(DecelerateInterpolator())
                    .start()
            }.start()
    }

    private fun shakeView(view: View) {
        view.animate()
            .setDuration(50)
            .xBy(8f)
            .withEndAction {
                view.animate()
                    .setDuration(50)
                    .xBy(-16f)
                    //.setInterpolator(DecelerateInterpolator())
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