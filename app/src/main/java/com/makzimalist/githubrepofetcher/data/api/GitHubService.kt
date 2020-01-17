package com.makzimalist.githubrepofetcher.data.api

import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GitHubService {

    @GET("/repositories")
    fun getAllRepos(): Single<RepositoryResponse> {
        return Single.just(RepositoryResponse())
    }
}