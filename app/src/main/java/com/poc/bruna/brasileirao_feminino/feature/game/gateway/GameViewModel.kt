package com.poc.bruna.brasileirao_feminino.feature.game.gateway

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.poc.bruna.brasileirao_feminino.feature.game.business.GameDatabaseDataSourceFactory
import com.poc.bruna.brasileirao_feminino.feature.game.business.GameRemoteDataSourceFactory
import com.poc.bruna.brasileirao_feminino.plugin.extension.asLiveData
import com.poc.bruna.brasileirao_feminino.plugin.model.entity.GameEntity
import javax.inject.Inject

class GameViewModel @Inject constructor(
    remoteDataSourceFactory:  GameRemoteDataSourceFactory,
    private val databaseDataSourceFactory: GameDatabaseDataSourceFactory
) : ViewModel() {

    private val _gamesMediatorLiveData = MediatorLiveData<PagedList<GameEntity>>()

    val gameLiveData = _gamesMediatorLiveData.asLiveData()
    val stateLiveData = remoteDataSourceFactory.stateLiveData.asLiveData()

    init {
        Log.d("Bruna", "GameViewModel.init: ${Thread.currentThread().name}")

        val remoteLivePagedList =
            LivePagedListBuilder(remoteDataSourceFactory, getPagedListConfig())
                .setBoundaryCallback(BoundaryCallback())
                .build()

        _gamesMediatorLiveData.addSource(remoteLivePagedList) {
            Log.d("Bruna", "GameViewModel.init._gamesMediatorLiveData: ${Thread.currentThread().name}")
            _gamesMediatorLiveData.value = it }
    }

    private fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config
            .Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LIST_SIZE)
            .setPageSize(LIST_SIZE)
            .build()
    }

    inner class BoundaryCallback : PagedList.BoundaryCallback<GameEntity>() {
        private val databaseLivePagedList =
            LivePagedListBuilder(databaseDataSourceFactory, getPagedListConfig()).build()

        init {
            Log.d("Bruna", "GameViewModel.BoundaryCallback: ${Thread.currentThread().name}")
        }

        override fun onZeroItemsLoaded() {
            Log.d("Bruna", "GameViewModel.onZeroItemsLoaded: ${Thread.currentThread().name}")
            super.onZeroItemsLoaded()
            _gamesMediatorLiveData.addSource(databaseLivePagedList) {
                Log.d("Bruna", "GameViewModel.onZeroItemsLoaded._gamesMediatorLiveData: ${Thread.currentThread().name}")
                _gamesMediatorLiveData.value = it
                _gamesMediatorLiveData.removeSource(databaseLivePagedList)
            }
        }
    }

    companion object {
        private const val LIST_SIZE = 20
    }
}