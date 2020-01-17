package com.makzimalist.githubrepofetcher.data.usecases

import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepositories
import io.reactivex.Single

class GetAllRepositoriesUseCaseImpl(
    private val gitHubRepositories: GitHubRepositories
): GetAllRepositoriesUseCase {

    override fun getAllRepositories(): Single<RepositoryResponse> {
        return gitHubRepositories.getAllRepositories()
    }
}