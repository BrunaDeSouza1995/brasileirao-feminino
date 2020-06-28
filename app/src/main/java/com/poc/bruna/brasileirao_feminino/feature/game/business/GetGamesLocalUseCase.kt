package com.poc.bruna.brasileirao_feminino.feature.game.business

import android.util.Log
import com.poc.bruna.brasileirao_feminino.base.business.UseCase
import com.poc.bruna.brasileirao_feminino.plugin.model.entity.GameEntity
import javax.inject.Inject

class GetGamesLocalUseCase @Inject constructor(
    private val repository: GameRepository
) : UseCase<Unit, List<GameEntity>>() {

    override suspend fun execute(param: Unit?): List<GameEntity> {
        Log.d("Bruna", "GetGamesLocalUseCase.execute: ${Thread.currentThread().name}")
        return repository.getGamesLocal()
    }
}