package com.poc.bruna.brasileiraofeminino.feature.game.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.poc.bruna.brasileiraofeminino.R
import com.poc.bruna.brasileiraofeminino.feature.game.gateway.GameViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_game.*
import javax.inject.Inject

class GameActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    @Inject
    lateinit var itemDecoration: GameItemDecoration

    private val viewModel: GameViewModel by viewModels { viewModelProvider }
    private val adapter = GameListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setUpList()
        onReceiveEvents()
    }

    private fun setUpList() {
        gameList.adapter = adapter
        gameList.addItemDecoration(itemDecoration)
    }

    private fun onReceiveEvents() {
        viewModel.gameLiveData.observe(this, Observer {
            Log.d("Bruna", "GameActivity.gameLiveData: ${Thread.currentThread().name}")
            adapter.submitList(it) })
        viewModel.stateLiveData.observe(this, Observer(adapter::submitNetwork))
    }
}
