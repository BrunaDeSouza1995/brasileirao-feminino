package com.poc.bruna.brasileiraofeminino.feature.game.business

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poc.bruna.brasileiraofeminino.plugin.extension.assertError
import com.poc.bruna.brasileiraofeminino.plugin.extension.assertSuccess
import com.poc.bruna.brasileiraofeminino.plugin.repository.GameRepositoryImpl
import com.poc.bruna.brasileiraofeminino.plugin.retrofit.Service
import com.poc.bruna.brasileiraofeminino.plugin.room.dao.GameDao
import com.poc.bruna.brasileiraofeminino.plugin.rule.DatabaseRule
import com.poc.bruna.brasileiraofeminino.plugin.rule.MockWebServerRule
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.ConnectException

@RunWith(AndroidJUnit4::class)
class GetGamesRemoteUseCaseTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val databaseRule = DatabaseRule()

    private lateinit var repository: GameRepository

    private lateinit var useCase: GetGamesRemoteUseCase

    private lateinit var gameDao: GameDao

    private lateinit var service: Service

    @Before
    fun setUp() {
        gameDao = databaseRule.database!!.gameDao()
        service = mockWebServerRule.service
        repository = GameRepositoryImpl(gameDao, service)
        useCase = GetGamesRemoteUseCase(repository)
    }

    @Test
    fun `when page is zero then return error result`() {
        useCase.assertError(0) {
            assertTrue(it is ConnectException)
        }
    }

    @Test
    fun `when page is one then return success result`() {
        useCase.assertSuccess(1) {
            assertTrue(it.isNotEmpty())
        }
    }
}