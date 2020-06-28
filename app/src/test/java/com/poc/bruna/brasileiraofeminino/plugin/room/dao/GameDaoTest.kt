package com.poc.bruna.brasileiraofeminino.plugin.room.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileiraofeminino.plugin.rule.DatabaseRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameDaoTest {

    @get:Rule
    val databaseRule = DatabaseRule()

    private var gameDao: GameDao? = null
    private val mock = GameEntity.mock()

    @Before
    fun createSupportFAQDAO() {
        gameDao = databaseRule.database?.gameDao()
    }

    @Test
    fun whenInsertItemThenGetItemSupportFAQ() {
        gameDao?.insert(mock)
        val games = gameDao?.getGames()
        assertTrue(games?.isNotEmpty() == true)
        assertEquals(games?.size, 1)
        assertEquals(games?.first(), mock)
    }
}
