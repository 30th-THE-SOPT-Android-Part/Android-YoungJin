package org.sopt.soptseminar.data.models.github

import org.sopt.soptseminar.models.RepositoryInfo

data class ResponseRepository(
    val id: Int,
    val name: String,
    val description: String?,
    val url: String,
) {
    fun toRepositoryInfo(repository: ResponseRepository): RepositoryInfo {
        return RepositoryInfo(repository.name,
            repository.description,
            repository.url,
            repository.id)
    }
}