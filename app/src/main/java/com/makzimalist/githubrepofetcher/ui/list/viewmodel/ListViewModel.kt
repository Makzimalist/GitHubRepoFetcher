package com.makzimalist.githubrepofetcher.ui.list.viewmodel

import android.util.Log
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
    val loadingSubject = BehaviorSubject.createDefault(false)

    fun requestRepositories() {
        loadingSubject.onNext(true)
        getAllRepositoriesUseCase.getAllRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.items }
            .doFinally { loadingSubject.onNext(false) }
            .subscribe({ dataSubject.onNext(it) },
                { Log.e(this.javaClass.simpleName, it.message, it) })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
