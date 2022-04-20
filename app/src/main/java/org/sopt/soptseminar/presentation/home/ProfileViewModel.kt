package org.sopt.soptseminar.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import org.sopt.soptseminar.models.UserInfo
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private var userInfo = MutableLiveData<UserInfo>()
    private var followers: ArrayList<FollowerInfo> = arrayListOf()
    private var following: ArrayList<FollowerInfo> = arrayListOf()
    private var repositories: ArrayList<RepositoryInfo> = arrayListOf()

    init {
        fetchGithubList()
    }

    private fun fetchGithubList() {
        // TODO repository에서 처리하기..
        followers = arrayListOf(
            FollowerInfo(
                1,
                "Kim Hyo Rim",
                "https://avatars.githubusercontent.com/u/59546818?v=4",
                "Junior in university majoring in CS💻",
                "https://github.com/KxxHyoRim"
            ),
            FollowerInfo(
                2,
                "KIM SU BEEN",
                "https://avatars.githubusercontent.com/u/62291759?v=4",
                "Android가 좋아~ㄴ드",
                "https://github.com/papajj06"
            ),
            FollowerInfo(
                3,
                "hyejung kim",
                "https://avatars.githubusercontent.com/u/68772751?v=4",
                "Backend Developer",
                "https://github.com/hyejungg"
            ),
            FollowerInfo(
                4,
                "Kim kihyeon",
                "https://avatars.githubusercontent.com/u/59547069?v=4",
                "김기현",
                "https://github.com/teagh82"
            ),
            FollowerInfo(
                5,
                "Eunseo Kim",
                "https://avatars.githubusercontent.com/u/70589857?v=4=",
                "Eunseo Kim",
                "https://github.com/eunseo2"
            ),
            FollowerInfo(
                6,
                "heewon",
                "https://avatars.githubusercontent.com/u/52772787?v=4",
                url = "https://github.com/ymcho24"
            ),
            FollowerInfo(
                7,
                "yoahn",
                "https://avatars.githubusercontent.com/u/46748334?v=4",
                url = "https://github.com/AYoungSn"
            ),
            FollowerInfo(
                8,
                "Hanhee Kang",
                "https://avatars.githubusercontent.com/u/68781598?v=4",
                "🍒현실의 문제를 소프트웨어로 해결하는 것이 재밌는 개발자입니다🍒",
                "https://github.com/kanghanhee"
            ),
        )
        following = arrayListOf(
            FollowerInfo(
                1,
                "YANG",
                "https://avatars.githubusercontent.com/u/48620082?v=4",
                url = "https://github.com/ynawhocodes"
            ),
            FollowerInfo(
                2,
                "Kim kihyeon",
                "https://avatars.githubusercontent.com/u/59547069?v=4",
                "김기현",
                "https://github.com/teagh82"
            ),
            FollowerInfo(
                3,
                "Hyein Kim",
                "https://avatars.githubusercontent.com/u/46434694?v=4",
                "매일 매일 자라는 중🌿",
                "https://github.com/hyeinisfree"
            ),
            FollowerInfo(
                4,
                "Sujeong Lim",
                "https://avatars.githubusercontent.com/u/41771874?v=4",
                "Studying Computer Engineering",
                "https://github.com/sio2whocodes"
            ),
            FollowerInfo(
                5,
                "heewon",
                "https://avatars.githubusercontent.com/u/52772787?v=4",
                url = "https://github.com/ymcho24"
            ),
            FollowerInfo(
                6,
                "yoahn",
                "https://avatars.githubusercontent.com/u/46748334?v=4",
                url = "https://github.com/AYoungSn"
            ),
            FollowerInfo(
                7,
                "Hanhee Kang",
                "https://avatars.githubusercontent.com/u/68781598?v=4",
                "🍒현실의 문제를 소프트웨어로 해결하는 것이 재밌는 개발자입니다🍒",
                "https://github.com/kanghanhee"
            ),
            FollowerInfo(
                8,
                "Kim Hyo Rim",
                "https://avatars.githubusercontent.com/u/59546818?v=4",
                "Junior in university majoring in CS💻",
                "https://github.com/KxxHyoRim"
            ),
            FollowerInfo(
                9,
                "KIM SU BEEN",
                "https://avatars.githubusercontent.com/u/62291759?v=4",
                "Android가 좋아~ㄴ드",
                "https://github.com/papajj06"
            ),
        )
        repositories = arrayListOf(
            RepositoryInfo(
                1,
                "DBP_t02",
                "2020 2학기 데이터베이스프로그래밍 기말 프로젝트",
                "https://github.com/youngjinc/Programmers"
            ),
            RepositoryInfo(
                2,
                "Programmers",
                "프로그래머스 문제 풀이",
                "https://github.com/youngjinc/Programmers"
            ),
            RepositoryInfo(
                3,
                "algorithm_study",
                "코딩테스트 대비 알고리즘 스터디 coterueljabara",
                "https://github.com/youngjinc/algorithm_study"
            ),
            RepositoryInfo(
                4,
                "sswu-oss",
                "Opensource software project for Sungshin Women's University",
                "https://github.com/youngjinc/sswu-oss"
            ),
            RepositoryInfo(
                5,
                "wishboard-android",
                "위시리스트 통합 관리 애플리케이션 안드로이드 레포",
                "https://github.com/hyeeyoung/wishboard-android"
            ),
            RepositoryInfo(
                6,
                "wishboard-server",
                "위시리스트 통합 관리 애플리케이션 서버 레포",
                "https://github.com/hyeeyoung/wishboard-server"
            ),
            RepositoryInfo(
                7,
                "wishboard-push-server",
                "위시리스트 통합 관리 애플리케이션 푸시 서버 레포",
                "https://github.com/hyeeyoung/wishboard-push-server"
            ),
            RepositoryInfo(
                8,
                "bokzip-android",
                "복지 가득한 bokzip 안드로이드 레포포포",
                "https://github.com/bokzip/bokzip-android"
            ),
            RepositoryInfo(
                9,
                "bokzip-server",
                "복지 가득한 bokzip 백엔드 레포포포",
                "https://github.com/bokzip/bokzip-backend"
            ),
            RepositoryInfo(10, "Pick", "결정을 도와주는 투표앱", "https://github.com/yougjinc/Pick"),
            RepositoryInfo(
                11,
                "NaengjanGo",
                "냉장고 식자제 유통기한 관리 서비스",
                "https://github.com/youngjinc/NaengjanGo"
            ),
        )
    }

    fun setUserInfo(userInfo: UserInfo) {
        this.userInfo.value = userInfo
    }

    fun getUserInfo(): LiveData<UserInfo> = userInfo
    fun getFollower(): ArrayList<FollowerInfo> = followers
    fun getFollowing(): ArrayList<FollowerInfo> = following
    fun getRepositories(): ArrayList<RepositoryInfo> = repositories
}