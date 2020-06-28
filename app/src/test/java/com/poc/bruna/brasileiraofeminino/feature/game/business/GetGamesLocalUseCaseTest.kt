package com.poc.bruna.brasileiraofeminino.feature.game.business

import com.poc.bruna.brasileiraofeminino.plugin.extension.assertError
import com.poc.bruna.brasileiraofeminino.plugin.extension.assertSuccess
import com.poc.bruna.brasileiraofeminino.plugin.model.entity.GameEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class GetGamesLocalUseCaseTest {

    @Mock
    lateinit var mockRepository: GameRepository

    lateinit var useCase: GetGamesLocalUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetGamesLocalUseCase(mockRepository)
    }

    @Test
    fun `when repository return games list then dispatch success result`() {
        val games = listOf(GameEntity.mock())

        `when`(mockRepository.getGamesLocal()).thenReturn(games)

        useCase.assertSuccess()
    }

    @Test
    fun `when repository throw exception then dispatch error result`() {
        `when`(mockRepository.getGamesLocal()).thenThrow(RuntimeException())

        useCase.assertError()
    }
}