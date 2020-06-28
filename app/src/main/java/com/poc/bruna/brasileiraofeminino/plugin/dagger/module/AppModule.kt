package com.poc.bruna.brasileiraofeminino.plugin.dagger.module

import android.content.Context
import com.poc.bruna.brasileiraofeminino.feature.application.BrasileiraoApplication
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