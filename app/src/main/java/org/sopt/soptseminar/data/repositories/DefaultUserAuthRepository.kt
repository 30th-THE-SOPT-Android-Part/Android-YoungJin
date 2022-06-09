package org.sopt.soptseminar.data.repositories

import org.sopt.soptseminar.data.models.db.LoginUserInfo
import org.sopt.soptseminar.data.models.db.UserDao
import org.sopt.soptseminar.data.models.sign.RequestSignIn
import org.sopt.soptseminar.data.models.sign.RequestSignUp
import org.sopt.soptseminar.data.services.SoptService
import org.sopt.soptseminar.domain.models.UserInfo
import org.sopt.soptseminar.domain.repositories.UserAuthRepository
import org.sopt.soptseminar.modules.datastore.UserPreferenceRepository
import org.sopt.soptseminar.util.UserSharedPreferencesManager
import javax.inject.Inject

class DefaultUserAuthRepository @Inject constructor(
    private val soptService: SoptService,
    private val userPreferenceRepo: UserPreferenceRepository,
    private val userSharedPreferencesManager: UserSharedPreferencesManager,
    private val userDao: UserDao,
) : UserAuthRepository {
    override suspend fun signIn(email: String, password: String): Pair<Boolean, String?> {

        return runCatching {
            soptService.postSignIn(RequestSignIn(email, password))
        }.fold({
            val data = it.body()?.data ?: return Pair(false, null)

            // 1. EncryptedSharedPreferences 사용
//            userSharedPreferencesManager.setUserInfo(
//                UserInfo(
//                    name = data.name,
//                    age = 24,
//                    mbti = "ISFP",
//                    university = "성신여대",
//                    major = "컴퓨터공학과",
//                    email = data.email
//                )
//            )

            // 2. DataStore 사용
            // TODO 7주차 과제 제출 후 주석 제거
//            userPreferenceRepo.setUserPreference(
//                UserInfo(
//                    name = data.name,
//                    age = 24,
//                    mbti = "ISFP",
//                    university = "성신여대",
//                    major = "컴퓨터공학과",
//                    email = data.email
//                )
//            )

            // 3. Room 사용
            userDao.saveUserInfo(
                LoginUserInfo(
                    name = data.name,
                    age = 24,
                    mbti = "ISFP",
                    university = "성신여대",
                    major = "컴퓨터공학과",
                    email = data.email
                )
            )

            Pair(true, data.name)
        }, {
            it.printStackTrace()
            Pair(false, null)
        })
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
    ): Pair<Boolean, Int?> {
        return runCatching {
            soptService.postSignUp(RequestSignUp(name, email, password))
        }.fold({
            Pair(true, it.code())
        }, {
            it.printStackTrace()
            Pair(true, null)
        })
    }
}