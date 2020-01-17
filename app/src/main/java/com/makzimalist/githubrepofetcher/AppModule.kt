package com.makzimalist.githubrepofetcher

import com.makzimalist.githubrepofetcher.data.api.GitHubApi
import com.makzimalist.githubrepofetcher.data.usecases.GetAllRepositoriesUseCase
import com.makzimalist.githubrepofetcher.data.usecases.GetAllRepositoriesUseCaseImpl
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepositories
import com.makzimalist.githubrepofetcher.domain.repo.GitHubRepositoriesImpl
import com.makzimalist.githubrepofetcher.ui.list.ListViewModel
import org.koin.dsl.module

val appModule = module {

    single<GitHubRepositories> { GitHubRepositoriesImpl(get()) }
    single { GitHubApi() }

    factory<GetAllRepositoriesUseCase> { GetAllRepositoriesUseCaseImpl(get()) }
    factory { ListViewModel() }

}