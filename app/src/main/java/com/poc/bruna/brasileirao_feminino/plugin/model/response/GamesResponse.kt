package com.poc.bruna.brasileirao_feminino.plugin.model.response

import com.google.gson.annotations.SerializedName

class GamesResponse : ArrayList<GameItem>()

data class GameItem(
    val id: Int,
    @SerializedName("data_realizacao")
    val realizationDate: String?,
    @SerializedName("hora_realizacao")
    val realizationTime: String?,
    @SerializedName("placar_oficial_visitante")
    val visitingScore: Int?,
    @SerializedName("placar_oficial_mandante")
    val principalScore: Int?,
    @SerializedName("placar_penaltis_visitante")
    val visitingPenaltyScore: Int?,
    @SerializedName("placar_penaltis_mandante")
    val principalPenaltyScore: Int?,
    @SerializedName("equipes")
    val teams: Teams,
    @SerializedName("sede")
    val thirst: Thirst,
    @SerializedName("transmissao")
    val streaming: Streaming?
)

data class Streaming(
    val label: String,
    val url: String
)

data class Teams(
    @SerializedName("mandante")
    val principal: Team,
    @SerializedName("visitante")
    val visiting: Team
)

data class Team(
    val id: Int,
    @SerializedName("nome_popular")
    val popularName: String,
    @SerializedName("sigla")
    val initials: String,
    @SerializedName("escudo")
    val shield: String
)

data class Thirst(
    @SerializedName("nome_popular")
    val popularName: String
)