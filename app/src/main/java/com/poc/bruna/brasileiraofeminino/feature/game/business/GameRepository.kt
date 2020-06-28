package com.poc.bruna.brasileiraofeminino.feature.game.business

import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity

interface GameRepository {
    fun getGamesLocal(): List<GameEntity>
    fun insertGameLocal(gameEntity: GameEntity)
    suspend fun getGamesRemote(page: Int): List<GameEntity>
}