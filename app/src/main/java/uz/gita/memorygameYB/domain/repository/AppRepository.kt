package uz.gita.memorygameYB.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.memorygameYB.data.models.GameModel
import uz.gita.memorygameYB.data.models.Level

interface AppRepository {
    fun loadDataByLevel(level: Level): Flow<List<GameModel>>
}