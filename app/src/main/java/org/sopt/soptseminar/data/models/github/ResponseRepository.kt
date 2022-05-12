package org.sopt.soptseminar.data.models.github

import com.google.gson.annotations.SerializedName
import org.sopt.soptseminar.domain.models.github.RepositoryInfo

data class ResponseRepository(
    val id: Int,
    val name: String,
    val description: String?,
    @SerializedName("html_url")
    val url: String,
) {
    fun toRepositoryInfo(repository: ResponseRepository): RepositoryInfo {
        return RepositoryInfo(repository.name,
            repository.description,
            repository.url,
            repository.id)
    }
}