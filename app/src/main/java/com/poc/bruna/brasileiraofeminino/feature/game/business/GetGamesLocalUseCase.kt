package com.poc.bruna.brasileiraofeminino.feature.game.business

import android.util.Log
import com.poc.bruna.brasileiraofeminino.base.business.UseCase
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import javax.inject.Inject

class GetGamesLocalUseCase @Inject constructor(
    private val repository: GameRepository
) : UseCase<Unit, List<GameEntity>>() {

    override suspend fun execute(param: Unit?): List<GameEntity> {
        Log.d("Bruna", "GetGamesLocalUseCase.execute: ${Thread.currentThread().name}")
        return repository.getGamesLocal()
    }
}