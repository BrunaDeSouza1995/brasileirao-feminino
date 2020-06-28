package com.poc.bruna.brasileiraofeminino.feature.game.business

import android.util.Log
import com.poc.bruna.brasileiraofeminino.base.business.UseCase
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import javax.inject.Inject

class GetGamesRemoteUseCase @Inject constructor(
    val repository: GameRepository
) : UseCase<Int, List<GameEntity>>() {

    override suspend fun execute(param: Int?): List<GameEntity> {
        Log.d("Bruna", "GetGamesRemoteUseCase.execute: ${Thread.currentThread().name}")
        return param?.let { repository.getGamesRemote(page = it) }.orEmpty()
    }
}