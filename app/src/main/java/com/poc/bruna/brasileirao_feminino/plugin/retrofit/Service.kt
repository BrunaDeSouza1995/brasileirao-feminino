package com.poc.bruna.brasileirao_feminino.plugin.retrofit

import com.poc.bruna.brasileirao_feminino.plugin.model.response.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("/tabela/56a50da4-cad0-4cf2-9633-661d598b7cfb/fase/primeira-fase-brasileiro-feminino-2020/rodada/{page}/jogos/")
    suspend fun getGames(@Path("page") page: Int): GamesResponse
}