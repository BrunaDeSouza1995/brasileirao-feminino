package com.poc.bruna.brasileiraofeminino.plugin.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getGames(): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: GameEntity)
}