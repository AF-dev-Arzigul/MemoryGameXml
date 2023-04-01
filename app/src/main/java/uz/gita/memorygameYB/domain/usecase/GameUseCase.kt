package uz.gita.memorygameYB.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level

interface GameUseCase {

    fun getDataByLevel(level: Level): Flow<List<GameModel>>
}