package com.poc.bruna.brasileiraofeminino.plugin.dagger.module

import android.content.Context
import com.poc.bruna.brasileiraofeminino.plugin.room.dao.GameDao
import com.poc.bruna.brasileiraofeminino.plugin.room.database.BrasileiraoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesBrasileiraoDatabase(context: Context): BrasileiraoDatabase {
        return BrasileiraoDatabase.getInstanceDatabase(context)
    }

    @Provides
    @Singleton
    fun providesGameDAO(database: BrasileiraoDatabase): GameDao = database.gameDao()
}