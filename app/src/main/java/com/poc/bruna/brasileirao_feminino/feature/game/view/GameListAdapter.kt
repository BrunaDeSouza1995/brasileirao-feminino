package com.poc.bruna.brasileirao_feminino.feature.game.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.poc.bruna.brasileirao_feminino.R
import com.poc.bruna.brasileirao_feminino.plugin.extension.gone
import com.poc.bruna.brasileirao_feminino.plugin.extension.visible
import com.poc.bruna.brasileirao_feminino.plugin.model.entity.GameEntity
import com.poc.bruna.brasileirao_feminino.plugin.model.model.FailedState
import com.poc.bruna.brasileirao_feminino.plugin.model.model.LoadedState
import com.poc.bruna.brasileirao_feminino.plugin.model.model.LoadingState
import com.poc.bruna.brasileirao_feminino.plugin.model.model.State
import kotlinx.android.synthetic.main.item_list_game.view.*
import kotlinx.android.synthetic.main.item_list_game_state.view.*

class GameListAdapter : PagedListAdapter<GameEntity, BaseViewHolder>(GameEntity.DIFF_CALLBACK) {

    private var state: State? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_list_game -> {
                val view = layoutInflater.inflate(R.layout.item_list_game, parent, false)
                GameViewHolder(view)
            }
            R.layout.item_list_game_state -> {
                val view = layoutInflater.inflate(R.layout.item_list_game_state, parent, false)
                GameStateViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_list_game -> getItem(position)?.let((holder as GameViewHolder)::bind)
            R.layout.item_list_game_state -> state?.let((holder as GameStateViewHolder)::bind)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) R.layout.item_list_game_state
        else R.layout.item_list_game
    }

    fun submitNetwork(newState: State) {
        val oldState = this.state
        val oldExtraRow = hasExtraRow()
        this.state = newState
        val newExtraRow = hasExtraRow()

        when {
            oldExtraRow != newExtraRow -> {
                if (oldExtraRow) notifyItemRemoved(itemCount)
                else notifyItemInserted(itemCount)
            }
            newExtraRow && oldState != newState -> {
                notifyItemChanged(itemCount - 1)
            }
        }
    }

    private fun hasExtraRow(): Boolean = state != LoadedState
}

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Any)
}

class GameViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private val dateText = itemView.gameDateText
    private val thirstText = itemView.gameThirstText
    private val principalInitialsText = itemView.gamePrincipalInitialsText
    private val principalTeamShieldImage = itemView.gamePrincipalTeamShieldImage
    private val principalScoreText = itemView.gamePrincipalScoreText
    private val visitingScoreText = itemView.gameVisitingScoreText
    private val visitingTeamShieldImage = itemView.gameVisitingTeamShieldImage
    private val visitingInitialsText = itemView.gameVisitingInitialsText

    override fun bind(item: Any) {
        val game = item as GameEntity

        dateText.text = game.realizationDate
        thirstText.text = game.thirst
        principalInitialsText.text = game.principalTeam
        principalScoreText.text = game.principalScore
        visitingScoreText.text = game.visitingScore
        visitingInitialsText.text = game.visitingTeam
        principalTeamShieldImage.load(game.principalTeamShield) {
            placeholder(R.drawable.ic_shield_gray)
            error(R.drawable.ic_shield_gray)
        }
        visitingTeamShieldImage.load(game.visitingTeamShield) {
            placeholder(R.drawable.ic_shield_white)
            error(R.drawable.ic_shield_white)
        }
    }
}

class GameStateViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val progress = itemView.gameProgress
    private val errorText = itemView.gameErrorText

    override fun bind(item: Any) {
        when (val state = item as State) {
            LoadingState -> {
                progress.visible()
                errorText.gone()
            }
            LoadedState -> {
                progress.gone()
                errorText.gone()
            }
            is FailedState -> {
                progress.gone()
                errorText.visible()
                errorText.text = state.message
            }
        }
    }
}
