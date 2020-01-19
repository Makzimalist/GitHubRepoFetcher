package com.makzimalist.githubrepofetcher.data.usecases

import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepoRepository
import io.reactivex.Single

class GetAllRepositoriesUseCaseImpl(
    private val gitHubRepoRepository: GitHubRepoRepository
): GetAllRepositoriesUseCase {

    override fun getAllRepositories(): Single<RepositoryResponse> {
        return gitHubRepoRepository.getAllRepositories()
    }
}