package com.makzimalist.githubrepofetcher.data.usecases

import com.makzimalist.githubrepofetcher.data.api.model.Repository
import io.reactivex.Flowable

interface GetRepositoryUseCase {

    fun getRepository(user: String, repository: String): Flowable<Repository>
}