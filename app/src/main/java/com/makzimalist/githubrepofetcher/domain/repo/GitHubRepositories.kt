package com.makzimalist.githubrepofetcher.domain.repo

import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Single

interface GitHubRepositories {

    fun getAllRepositories(): Single<RepositoryResponse>
}