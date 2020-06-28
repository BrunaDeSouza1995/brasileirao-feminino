package com.poc.bruna.brasileirao_feminino.feature.game.business

import com.poc.bruna.brasileirao_feminino.plugin.model.entity.GameEntity

interface GameRepository {
    fun getGamesLocal(): List<GameEntity>
    fun insertGameLocal(gameEntity: GameEntity)
    suspend fun getGamesRemote(page: Int): List<GameEntity>
}