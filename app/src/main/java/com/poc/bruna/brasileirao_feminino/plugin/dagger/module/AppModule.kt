package com.poc.bruna.brasileirao_feminino.plugin.dagger.module

import android.content.Context
import com.poc.bruna.brasileirao_feminino.feature.application.BrasileiraoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: BrasileiraoApplication): Context {
        return application.applicationContext
    }
}