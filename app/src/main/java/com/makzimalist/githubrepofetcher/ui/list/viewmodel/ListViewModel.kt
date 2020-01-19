package com.makzimalist.githubrepofetcher.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.data.usecases.GetAllRepositoriesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import org.koin.core.inject

class ListViewModel : ViewModel(), KoinComponent {

    private val getAllRepositoriesUseCase: GetAllRepositoriesUseCase by inject()

    private val compositeDisposable = CompositeDisposable()

    val dataSubject = BehaviorSubject.create<List<Repository>>()

    fun requestRepositories() {
        getAllRepositoriesUseCase.getAllRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.items }
            .subscribe({ dataSubject.onNext(it) }, { it.printStackTrace() })
            .addTo(compositeDisposable)
    }
}
