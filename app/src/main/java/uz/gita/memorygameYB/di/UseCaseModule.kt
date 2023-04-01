package uz.gita.memorygameYB.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.memorygameYB.domain.usecase.GameUseCase
import uz.gita.memorygameYB.domain.usecase.impl.GameUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGameUseCase(impl: GameUseCaseImpl): GameUseCase
}