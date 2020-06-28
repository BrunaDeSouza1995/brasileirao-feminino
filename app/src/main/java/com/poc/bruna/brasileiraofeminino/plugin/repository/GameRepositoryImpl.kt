package com.poc.bruna.brasileiraofeminino.plugin.repository

import android.util.Log
import com.poc.bruna.brasileiraofeminino.feature.game.business.GameRepository
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileiraofeminino.plugin.retrofit.Service
import com.poc.bruna.brasileiraofeminino.plugin.room.dao.GameDao
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDao: GameDao,
    private val service: Service
) : GameRepository {

    override fun getGamesLocal(): List<GameEntity> {
        Log.d("Bruna", "GameRepositoryImpl.getGamesLocal: ${Thread.currentThread().name}")
        return gameDao.getGames()
    }

    override fun insertGameLocal(gameEntity: GameEntity) {
        Log.d("Bruna", "GameRepositoryImpl.insertGameLocal: ${Thread.currentThread().name}")
        gameDao.insert(gameEntity)
    }

    override suspend fun getGamesRemote(page: Int): List<GameEntity> {
        Log.d("Bruna", "GameRepositoryImpl.getGamesRemote: ${Thread.currentThread().name}")
        val games = service.getGames(page).map(GameEntity.Companion::converterByGameItem)
        games.forEach(gameDao::insert)
        return games
    }
}