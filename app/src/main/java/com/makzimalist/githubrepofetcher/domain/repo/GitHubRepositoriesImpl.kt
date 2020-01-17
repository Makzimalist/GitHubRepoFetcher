package com.makzimalist.githubrepofetcher.domain.repo

import com.makzimalist.githubrepofetcher.data.api.GitHubApi
import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Single

class GitHubRepositoriesImpl(
    private val gitHubApi: GitHubApi
) : GitHubRepositories {

    override fun getAllRepositories(): Single<RepositoryResponse> {
        return gitHubApi.gitHubService.getAllRepos()
    }
}