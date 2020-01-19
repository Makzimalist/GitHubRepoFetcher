package com.makzimalist.githubrepofetcher.data.api

import com.makzimalist.githubrepofetcher.data.api.model.Repository
import com.makzimalist.githubrepofetcher.data.api.model.RepositoryResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("/search/repositories")
    fun getAllRepos(@Query("q") query: String = "stars:>=1"): Single<RepositoryResponse>

    @GET("/repos/{user}/{repository}")
    fun getRepositoryFor(@Path("user") user: String, @Path("repository") repository: String): Flowable<Repository>

}