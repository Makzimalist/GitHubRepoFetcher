package com.makzimalist.githubrepofetcher.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.makzimalist.githubrepofetcher.data.usecases.GetAllRepositoriesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class ListViewModel : ViewModel(), KoinComponent {

    private val getAllRepositoriesUseCase: GetAllRepositoriesUseCase by inject()


    fun requestRepositories() {
        getAllRepositoriesUseCase.getAllRepositories()
            .doOnEvent { t1, t2 ->  Log.e("TAG", "$t1")}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}
