package com.poc.bruna.brasileiraofeminino.plugin.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poc.bruna.brasileiraofeminino.feature.game.business.GameRepository
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileiraofeminino.plugin.rule.DatabaseRule
import com.poc.bruna.brasileiraofeminino.plugin.rule.MockWebServerRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException

@RunWith(AndroidJUnit4::class)
class GameRepositoryImplTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val databaseRule = DatabaseRule()

    lateinit var repository: GameRepository

    @Before
    fun setUp() {
        val gameDao = databaseRule.database!!.gameDao()
        val service = mockWebServerRule.service
        repository = GameRepositoryImpl(gameDao, service)
    }

    @Test
    fun `when load games in the page 0 then return nothing`() {
        runBlocking {
            val games = repository.getGamesRemote(0)

            assertTrue(games.isNullOrEmpty())
        }
    }

    @Test
    fun `when load games in the page 1 then return games list`() {
        runBlocking {
            val games = repository.getGamesRemote(1)

            assertTrue(games.isNotEmpty())
            games.first().apply {
                assertEquals(241096, id)
                assertEquals("08 February 2020 - 15:00", realizationDate)
                assertEquals("Caçador", thirst)
                assertEquals("7", principalScore)
                assertEquals(null, principalPenaltyScore)
                assertEquals("Avaí/Kindermann", principalTeam)
                assertEquals("0", visitingScore)
                assertEquals(null, visitingPenaltyScore)
                assertEquals("Vitória", visitingTeam)
                assertEquals(
                    "https://s.glbimg.com/es/sde/f/organizacoes/2020/02/06/Avaí_Kindermann.svg",
                    principalTeamShield
                )
                assertEquals(
                    "https://s.glbimg.com/es/sde/f/organizacoes/2018/03/11/vitoria.svg",
                    visitingTeamShield
                )
            }
        }
    }

    @Test(expected = HttpException::class)
    fun `when load games in the page 3 the return throw exception`() {
        runBlocking {
            repository.getGamesRemote(3)
        }
    }

    @Test
    fun `when insert data then return same data`() {
        val mock = GameEntity.mock()

        repository.insertGameLocal(mock)

        assertTrue(repository.getGamesLocal().isNotEmpty())
    }
}