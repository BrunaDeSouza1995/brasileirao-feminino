package com.poc.bruna.brasileirao_feminino.plugin.dagger.application

import com.poc.bruna.brasileirao_feminino.feature.application.BrasileiraoApplication
import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.ActivityModule
import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.AppModule
import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.RepositoryModule
import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.RetrofitModule
import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.RoomModule
import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        RepositoryModule::class,
        RetrofitModule::class,
        RoomModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(application: BrasileiraoApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: BrasileiraoApplication): Builder

        fun build(): AppComponent
    }
}
