package com.makzimalist.githubrepofetcher.ui.detail.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.makzimalist.githubrepofetcher.R

class DetailsViewModel : ViewModel() {

    fun openBrowser(context: Context?, url: String) {
        val builder = CustomTabsIntent.Builder()
        context?.let { builder.setToolbarColor(ContextCompat.getColor(it, R.color.colorPrimary)) }
        val customTabsIntent = builder.build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(url)) }
    }

}