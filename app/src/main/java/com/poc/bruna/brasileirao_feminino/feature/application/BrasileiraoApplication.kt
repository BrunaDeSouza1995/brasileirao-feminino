package com.poc.bruna.brasileirao_feminino.feature.application

import android.app.Application
import com.poc.bruna.brasileirao_feminino.plugin.dagger.application.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BrasileiraoApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun initDagger() {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}
