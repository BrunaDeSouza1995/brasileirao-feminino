package com.poc.bruna.brasileirao_feminino.feature.game.business

import android.util.Log
import com.poc.bruna.brasileirao_feminino.base.business.UseCase
import com.poc.bruna.brasileirao_feminino.plugin.model.entity.GameEntity
import javax.inject.Inject

class GetGamesRemoteUseCase @Inject constructor(
    val repository: GameRepository
) : UseCase<Int, List<GameEntity>>() {

    override suspend fun execute(param: Int?): List<GameEntity> {
        Log.d("Bruna", "GetGamesRemoteUseCase.execute: ${Thread.currentThread().name}")
        return param?.let { repository.getGamesRemote(page = it) }.orEmpty()
    }
}