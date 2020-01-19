package com.makzimalist.githubrepofetcher.domain.repo

import com.makzimalist.githubrepofetcher.data.api.GitHubApi
import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Flowable
import io.reactivex.Single

class GitHubRepoRepositoryImpl(
    private val gitHubApi: GitHubApi
) : GitHubRepoRepository {

    override fun getAllRepositories(): Single<RepositoryResponse> {
        return gitHubApi.gitHubService.getAllRepos()
    }

    override fun getRepository(user: String, repository: String): Flowable<Repository> {
        return gitHubApi.gitHubService.getRepositoryFor(user, repository)
    }
}