package com.makzimalist.githubrepofetcher.domain.repo

import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface GitHubRepoRepository {

    fun getAllRepositories(): Single<RepositoryResponse>

    fun getRepository(user: String, repository: String): Flowable<Repository>
}