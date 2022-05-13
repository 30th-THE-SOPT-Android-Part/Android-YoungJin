package org.sopt.soptseminar.data.repositories

import org.sopt.soptseminar.data.models.sign.RequestSignIn
import org.sopt.soptseminar.data.models.sign.RequestSignUp
import org.sopt.soptseminar.data.services.SoptService
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.repositories.UserAuthRepository
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import javax.inject.Inject

class DefaultUserAuthRepository @Inject constructor(
    private val soptService: SoptService,
    private val userPreferenceRepo: UserPreferenceRepository,
) : UserAuthRepository {
    override suspend fun signIn(email: String, password: String): Pair<Boolean, String?> {
        runCatching {
            soptService.postSignIn(RequestSignIn(email, password))
        }.fold({
            val data = it.body()?.data ?: return Pair(false, null)
            userPreferenceRepo.setUserPreference(
                UserInfo(
                    name = data.name,
                    age = 24,
                    mbti = "ISFP",
                    university = "성신여대",
                    major = "컴퓨터공학과",
                    email = "cyjin6@naver.com"
                )
            )
            return Pair(true, data.name)
        }, {
            it.printStackTrace()
            return Pair(false, null)
        })
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
    ): Pair<Boolean, Int?> {
        runCatching {
            soptService.postSignUp(RequestSignUp(name, email, password))
        }.fold({
            return Pair(true, it.code())
        }, {
            it.printStackTrace()
            return Pair(true, null)
        })
    }
}