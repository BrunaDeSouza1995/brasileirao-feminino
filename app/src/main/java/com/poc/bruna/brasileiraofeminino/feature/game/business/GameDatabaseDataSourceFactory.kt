package com.poc.bruna.brasileiraofeminino.feature.game.business

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileiraofeminino.plugin.paging.PAGE_ONE
import com.poc.bruna.brasileiraofeminino.plugin.paging.PAGE_ZERO
import javax.inject.Inject

class GameDatabaseDataSourceFactory @Inject constructor(val getGamesUseCase: GetGamesLocalUseCase) :
    DataSource.Factory<Int, GameEntity>() {

    override fun create(): DataSource<Int, GameEntity> = DatabasePageKeyedDataSource()

    inner class DatabasePageKeyedDataSource : PageKeyedDataSource<Int, GameEntity>() {

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, GameEntity>
        ) {
            Log.d("Bruna", "GameDatabaseDataSourceFactory.loadInitial: ${Thread.currentThread().name}")

            getGamesUseCase(
                onSuccess = {
                    Log.d("Bruna", "GameDatabaseDataSourceFactory.loadInitial.onSuccess: ${Thread.currentThread().name}")
                    if (it.isNotEmpty()) {
                        Log.d("Bruna", "GameDatabaseDataSourceFactory.loadInitial.onSuccess.it ${it}: ${Thread.currentThread().name}")
                        callback.onResult(it, PAGE_ZERO, PAGE_ONE)
                    }
                }
            )
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, GameEntity>
        ) = Unit

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, GameEntity>
        ) = Unit
    }
}