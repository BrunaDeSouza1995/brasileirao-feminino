package com.poc.bruna.brasileirao_feminino.plugin.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.poc.bruna.brasileirao_feminino.feature.game.gateway.GameViewModel
import com.poc.bruna.brasileirao_feminino.plugin.dagger.viewmodel.ViewModelFactory
import com.poc.bruna.brasileirao_feminino.plugin.dagger.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun providesGameViewModel(mainViewModel: GameViewModel): ViewModel
}
