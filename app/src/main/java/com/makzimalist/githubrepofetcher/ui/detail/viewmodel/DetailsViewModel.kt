package com.makzimalist.githubrepofetcher.ui.detail.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.makzimalist.githubrepofetcher.R
import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.data.usecases.GetRepositoryUseCase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

class DetailsViewModel : ViewModel(), KoinComponent {

    private val getAllRepositoriesUseCase: GetRepositoryUseCase by inject()
    private val compositeDisposable = CompositeDisposable()

    val updateSubject  = BehaviorSubject.create<Repository>()

    fun openBrowser(context: Context?, url: String) {
        val builder = CustomTabsIntent.Builder()
        context?.let { builder.setToolbarColor(ContextCompat.getColor(it, R.color.colorPrimary)) }
        val customTabsIntent = builder.build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.let { customTabsIntent.launchUrl(it, Uri.parse(url)) }
    }

    fun startPolling(repository: Repository) {
        // maybe not the best solution but it works as intended ¯\_(ツ)_/¯
        Flowable.interval(10, 10, TimeUnit.SECONDS)
            .subscribe {
                getAllRepositoriesUseCase.getRepository(repository.owner.name, repository.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( {
                        Log.wtf(javaClass.simpleName, repository.name)
                        updateSubject.onNext(repository)
                    }, {
                        Log.e(javaClass.simpleName, it.message, it)
                    }).addTo(compositeDisposable)
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}