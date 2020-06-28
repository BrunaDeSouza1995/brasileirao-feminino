package com.poc.bruna.brasileirao_feminino.plugin.dagger.module

import com.poc.bruna.brasileirao_feminino.feature.game.business.GameRepository
import com.poc.bruna.brasileirao_feminino.plugin.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun providesRepository(repository: GameRepositoryImpl): GameRepository
}