package com.poc.bruna.brasileiraofeminino.plugin.dagger.module

import com.poc.bruna.brasileiraofeminino.feature.game.business.GameRepository
import com.poc.bruna.brasileiraofeminino.plugin.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun providesRepository(repository: GameRepositoryImpl): GameRepository
}