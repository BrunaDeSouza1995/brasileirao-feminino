package com.poc.bruna.brasileirao_feminino.plugin.dagger.module

import com.poc.bruna.brasileirao_feminino.feature.game.view.GameActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeGameActivity(): GameActivity
}