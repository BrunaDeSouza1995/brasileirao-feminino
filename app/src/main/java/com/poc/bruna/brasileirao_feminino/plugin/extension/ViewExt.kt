package com.poc.bruna.brasileirao_feminino.plugin.extension

import android.view.View

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}