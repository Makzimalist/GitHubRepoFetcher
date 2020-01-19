package com.makzimalist.githubrepofetcher.extension

import android.view.View

/**
 * Extension file for all types of views
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}