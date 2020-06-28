package com.poc.bruna.brasileiraofeminino.plugin.model.entity

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poc.bruna.brasileiraofeminino.plugin.extension.formattedDate
import com.poc.bruna.brasileiraofeminino.plugin.model.response.GameItem

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey val id: Int,
    val realizationDate: String,
    val thirst: String,
    val principalScore: String,
    val principalPenaltyScore: String?,
    val principalTeam: String,
    val principalTeamShield: String,
    val visitingScore: String,
    val visitingPenaltyScore: String?,
    val visitingTeam: String,
    val visitingTeamShield: String
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameEntity>() {
            override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }

        fun converterByGameItem(game: GameItem): GameEntity {
            return GameEntity(
                id = game.id,
                realizationDate = game.realizationDate.formattedDate(),
                thirst = game.thirst.popularName,
                visitingScore = (game.visitingScore ?: 0).toString(),
                visitingPenaltyScore = game.visitingPenaltyScore?.toString(),
                visitingTeam = game.teams.visiting.popularName,
                visitingTeamShield = game.teams.visiting.shield,
                principalPenaltyScore = game.principalPenaltyScore?.toString(),
                principalScore = (game.principalScore ?: 0).toString(),
                principalTeam = game.teams.principal.popularName,
                principalTeamShield = game.teams.principal.shield
            )
        }

        fun mock(): GameEntity {
            return GameEntity(
                id = 0,
                realizationDate = "realizationDate",
                thirst = "thirst",
                visitingScore = "0",
                visitingPenaltyScore = null,
                visitingTeam = "visiting",
                visitingTeamShield = "visitingShield",
                principalPenaltyScore = null,
                principalScore = "0",
                principalTeam = "principal",
                principalTeamShield = "principalShield"
            )
        }
    }
}