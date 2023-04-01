package uz.gita.memorygameYB.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.memorygameYB.R
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val data = ArrayList<GameModel>()

    init {
        loadData()
    }

    private fun loadData() {
        data.add(GameModel(1, R.drawable.snake))
        data.add(GameModel(2, R.drawable.bird))
        data.add(GameModel(3, R.drawable.kangaroo))
        data.add(GameModel(4, R.drawable.cow))
        data.add(GameModel(5, R.drawable.sloth))
        data.add(GameModel(6, R.drawable.hippo))
        data.add(GameModel(7, R.drawable.fish))
        data.add(GameModel(8, R.drawable.penguin))
        data.add(GameModel(9, R.drawable.tiger))
        data.add(GameModel(10, R.drawable.bear))
        data.add(GameModel(11, R.drawable.camel))
        data.add(GameModel(12, R.drawable.rabbit))
        data.add(GameModel(13, R.drawable.shark))
        data.add(GameModel(14, R.drawable.elephant))
        data.add(GameModel(15, R.drawable.gorilla))
        data.add(GameModel(16, R.drawable.bee))
        data.add(GameModel(17, R.drawable.crocodile))
        data.add(GameModel(18, R.drawable.owl))
        data.add(GameModel(19, R.drawable.cat))
        data.add(GameModel(20, R.drawable.dog))
        data.add(GameModel(21, R.drawable.lion))
        data.add(GameModel(22, R.drawable.panda))
        data.add(GameModel(23, R.drawable.zebra))
        data.add(GameModel(24, R.drawable.tortoise))
        data.add(GameModel(25, R.drawable.ruster))
        data.add(GameModel(26, R.drawable.delphin))
    }

    override fun loadDataByLevel(level: Level): Flow<List<GameModel>> = flow {
        val count = level.x * level.y
        data.shuffle()
        emit(data.subList(0, count / 2))
    }
}