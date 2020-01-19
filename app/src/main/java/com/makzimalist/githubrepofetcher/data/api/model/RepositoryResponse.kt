package com.makzimalist.githubrepofetcher.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RepositoryResponse(val total_count: Int, val items: List<Repository>)

@Parcelize
data class Repository(
    val id: Int,
    val name: String,
    val owner: Owner,
    val html_url: String,
    val description: String,
    val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("watchers_count") val watchers: Int,
    @SerializedName("forks_count") val forks: Int
) : Parcelable

@Parcelize
data class Owner(@SerializedName("login") val name: String) : Parcelable