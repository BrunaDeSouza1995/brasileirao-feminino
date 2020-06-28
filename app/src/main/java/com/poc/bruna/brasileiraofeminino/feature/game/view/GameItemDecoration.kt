package com.poc.bruna.brasileiraofeminino.feature.game.view

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.poc.bruna.brasileiraofeminino.R
import javax.inject.Inject

class GameItemDecoration @Inject constructor(context: Context?) : RecyclerView.ItemDecoration() {

    private val spaceHeight = context?.resources?.getDimension(R.dimen.margin_default)?.toInt() ?: 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) top = spaceHeight
            left = spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}
