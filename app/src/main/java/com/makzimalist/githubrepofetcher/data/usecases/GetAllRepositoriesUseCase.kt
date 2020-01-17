package com.makzimalist.githubrepofetcher.data.usecases

import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Single

interface GetAllRepositoriesUseCase {

    fun getAllRepositories(): Single<RepositoryResponse>
}