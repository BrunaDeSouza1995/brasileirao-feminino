package com.poc.bruna.brasileiraofeminino.plugin.dagger.module

import com.poc.bruna.brasileiraofeminino.feature.game.view.GameActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeGameActivity(): GameActivity
}