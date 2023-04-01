package uz.gita.memorygameYB.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level
import uz.gita.memorygameYB.domain.repository.AppRepository
import uz.gita.memorygameYB.domain.usecase.GameUseCase
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(private val repository: AppRepository) : GameUseCase {

    override fun getDataByLevel(level: Level): Flow<List<GameModel>> = flow {
        val result = ArrayList<GameModel>()
        val list = repository.loadDataByLevel(level)

        list.collect {
            result.addAll(it)
            result.addAll(it)
        }
        result.shuffle()
        emit(result)
    }.flowOn(Dispatchers.Default)
}