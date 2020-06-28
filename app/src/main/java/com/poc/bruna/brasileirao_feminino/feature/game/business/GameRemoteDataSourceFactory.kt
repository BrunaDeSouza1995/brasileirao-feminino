package com.poc.bruna.brasileirao_feminino.feature.game.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.poc.bruna.brasileirao_feminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileirao_feminino.plugin.model.model.FailedState
import com.poc.bruna.brasileirao_feminino.plugin.model.model.LoadedState
import com.poc.bruna.brasileirao_feminino.plugin.model.model.LoadingState
import com.poc.bruna.brasileirao_feminino.plugin.model.model.State
import com.poc.bruna.brasileirao_feminino.plugin.paging.PAGE_ONE
import com.poc.bruna.brasileirao_feminino.plugin.paging.PAGE_TWO
import com.poc.bruna.brasileirao_feminino.plugin.paging.getAdjacentPageKey
import javax.inject.Inject

class GameRemoteDataSourceFactory @Inject constructor(
    private val getGamesRemoteUseCase: GetGamesRemoteUseCase
) : DataSource.Factory<Int, GameEntity>() {

    val stateLiveData = MutableLiveData<State>()

    override fun create(): DataSource<Int, GameEntity> = RemotePageKeyedDataSource()

    inner class RemotePageKeyedDataSource : PageKeyedDataSource<Int, GameEntity>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, GameEntity>
        ) {
            Log.d("Bruna", "GameRemoteDataSourceFactory.loadInitial: ${Thread.currentThread().name}")
            stateLiveData.postValue(LoadingState)
            getGamesRemoteUseCase(
                PAGE_ONE,
                onSuccess = {
                    Log.d("Bruna", "GameRemoteDataSourceFactory.loadInitial.onSuccess: ${Thread.currentThread().name}")
                    callback.onResult(it, PAGE_ONE, PAGE_TWO)
                    stateLiveData.postValue(LoadedState)
                },
                onError = {
                    Log.d("Bruna", "GameRemoteDataSourceFactory.loadInitial.onError: ${Thread.currentThread().name}")
                    callback.onResult(listOf(), PAGE_ONE, PAGE_TWO)
                    stateLiveData.postValue(FailedState(it.message))
                }
            )
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, GameEntity>
        ) {
            Log.d("Bruna", "GameRemoteDataSourceFactory.loadAfter: ${Thread.currentThread().name}")

            val page = params.key
            stateLiveData.postValue(LoadingState)
            getGamesRemoteUseCase(page,
                onSuccess = {
                    Log.d("Bruna", "GameRemoteDataSourceFactory.loadAfter.onSuccess: ${Thread.currentThread().name}")

                    callback.onResult(it, params.getAdjacentPageKey())
                    stateLiveData.postValue(LoadedState)
                },
                onError = {
                    Log.d("Bruna", "GameRemoteDataSourceFactory.loadAfter.onError: ${Thread.currentThread().name}")
                    callback.onResult(listOf(), page)
                    stateLiveData.postValue(FailedState(it.message))
                })
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, GameEntity>
        ) = Unit
    }
}