package com.makzimalist.githubrepofetcher

import com.makzimalist.githubrepofetcher.data.api.GitHubApi
import com.makzimalist.githubrepofetcher.data.usecases.GetAllRepositoriesUseCase
import com.makzimalist.githubrepofetcher.data.usecases.GetAllRepositoriesUseCaseImpl
import com.makzimalist.githubrepofetcher.data.usecases.GetRepositoryUseCase
import com.makzimalist.githubrepofetcher.data.usecases.GetRepositoryUseCaseImpl
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepoRepository
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepoRepositoryImpl
import com.makzimalist.githubrepofetcher.ui.list.viewmodel.ListViewModel
import org.koin.dsl.module

val appModule = module {

    single<GitHubRepoRepository> { GitHubRepoRepositoryImpl(get()) }
    single { GitHubApi() }

    factory<GetAllRepositoriesUseCase> { GetAllRepositoriesUseCaseImpl(get()) }
    factory<GetRepositoryUseCase> { GetRepositoryUseCaseImpl(get()) }
    factory { ListViewModel() }
}