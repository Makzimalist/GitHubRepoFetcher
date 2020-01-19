package com.makzimalist.githubrepofetcher.data.usecases

import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepoRepository
import io.reactivex.Flowable

class GetRepositoryUseCaseImpl(
    private val gitHubRepoRepository: GitHubRepoRepository
) : GetRepositoryUseCase {

    override fun getRepository(user: String, repository: String): Flowable<Repository> {
        return gitHubRepoRepository.getRepository(user, repository)
    }
}