package org.sopt.soptseminar.data.datasources

import org.sopt.soptseminar.models.FollowerInfo
import org.sopt.soptseminar.models.RepositoryInfo
import javax.inject.Inject

class GithubProfileRemoteDataSource @Inject constructor() {
    fun fetchFollowers(): List<FollowerInfo> {
        // TODO Fetching followerList from remote
        return listOf(
            FollowerInfo(
                "Kim Hyo Rim",
                "https://avatars.githubusercontent.com/u/59546818?v=4",
                "https://github.com/KxxHyoRim",
                "Junior in university majoring in CS💻"
            ),
            FollowerInfo(
                "KIM SU BEEN",
                "https://avatars.githubusercontent.com/u/62291759?v=4",
                "https://github.com/papajj06",
                "Android가 좋아~ㄴ드"
            ),
            FollowerInfo(
                "hyejung kim",
                "https://avatars.githubusercontent.com/u/68772751?v=4",
                "https://github.com/hyejungg",
                "Backend Developer"
            ),
            FollowerInfo(
                "Kim kihyeon",
                "https://avatars.githubusercontent.com/u/59547069?v=4",
                "https://github.com/teagh82",
                "김기현"
            ),
            FollowerInfo(
                "Eunseo Kim",
                "https://avatars.githubusercontent.com/u/70589857?v=4=",
                "https://github.com/eunseo2",
                "Eunseo Kim"
            ),
            FollowerInfo(
                "heewon",
                "https://avatars.githubusercontent.com/u/52772787?v=4",
                "https://github.com/ymcho24"
            ),
            FollowerInfo(
                "yoahn",
                "https://github.com/AYoungSn",
                "https://avatars.githubusercontent.com/u/46748334?v=4"

            ),
            FollowerInfo(
                "Hanhee Kang",
                "https://avatars.githubusercontent.com/u/68781598?v=4",
                "https://github.com/kanghanhee",
                "🍒현실의 문제를 소프트웨어로 해결하는 것이 재밌는 개발자입니다🍒"
            ),
        )
    }

    fun fetchFollowing(): List<FollowerInfo> {
        // TODO Fetching followingList from remote
        return listOf(
            FollowerInfo(
                "YANG",
                "https://avatars.githubusercontent.com/u/48620082?v=4",
                "https://github.com/ynawhocodes"
            ),
            FollowerInfo(
                "Kim kihyeon",
                "https://avatars.githubusercontent.com/u/59547069?v=4",
                "https://github.com/teagh82",
                "김기현"
            ),
            FollowerInfo(
                "Hyein Kim",
                "https://avatars.githubusercontent.com/u/46434694?v=4",
                "https://github.com/hyeinisfree",
                "매일 매일 자라는 중🌿"
            ),
            FollowerInfo(
                "Sujeong Lim",
                "https://avatars.githubusercontent.com/u/41771874?v=4",
                "https://github.com/sio2whocodes",
                "Studying Computer Engineering"
            ),
            FollowerInfo(
                "heewon",
                "https://avatars.githubusercontent.com/u/52772787?v=4",
                "https://github.com/ymcho24"
            ),
            FollowerInfo(
                "yoahn",
                "https://avatars.githubusercontent.com/u/46748334?v=4",
                "https://github.com/AYoungSn"
            ),
            FollowerInfo(
                "Hanhee Kang",
                "https://avatars.githubusercontent.com/u/68781598?v=4",
                "https://github.com/kanghanhee",
                "🍒현실의 문제를 소프트웨어로 해결하는 것이 재밌는 개발자입니다🍒"
            ),
            FollowerInfo(
                "Kim Hyo Rim",
                "https://avatars.githubusercontent.com/u/59546818?v=4",
                "https://github.com/KxxHyoRim",
                "Junior in university majoring in CS💻"
            ),
            FollowerInfo(
                "KIM SU BEEN",
                "https://avatars.githubusercontent.com/u/62291759?v=4",
                "https://github.com/papajj06",
                "Android가 좋아~ㄴ드"
            )
        )
    }

    fun fetchRepositories(): List<RepositoryInfo> {
        // TODO Fetching repositoryList from remote
        return listOf(
            RepositoryInfo(
                "DBP_t02",
                "2020 2학기 데이터베이스프로그래밍 기말 프로젝트",
                "https://github.com/youngjinc/Programmers"
            ),
            RepositoryInfo(
                "Programmers",
                "프로그래머스 문제 풀이",
                "https://github.com/youngjinc/Programmers"
            ),
            RepositoryInfo(
                "algorithm_study",
                "코딩테스트 대비 알고리즘 스터디 coterueljabara",
                "https://github.com/youngjinc/algorithm_study"
            ),
            RepositoryInfo(
                "sswu-oss",
                "Opensource software project for Sungshin Women's University",
                "https://github.com/youngjinc/sswu-oss"
            ),
            RepositoryInfo(
                "wishboard-android",
                "위시리스트 통합 관리 애플리케이션 안드로이드 레포",
                "https://github.com/hyeeyoung/wishboard-android"
            ),
            RepositoryInfo(
                "wishboard-server",
                "위시리스트 통합 관리 애플리케이션 서버 레포",
                "https://github.com/hyeeyoung/wishboard-server"
            ),
            RepositoryInfo(
                "wishboard-push-server",
                "위시리스트 통합 관리 애플리케이션 푸시 서버 레포",
                "https://github.com/hyeeyoung/wishboard-push-server"
            ),
            RepositoryInfo(
                "bokzip-android",
                "복지 가득한 bokzip 안드로이드 레포포포",
                "https://github.com/bokzip/bokzip-android"
            ),
            RepositoryInfo(
                "bokzip-server",
                "복지 가득한 bokzip 백엔드 레포포포",
                "https://github.com/bokzip/bokzip-backend"
            ),
            RepositoryInfo("Pick", "결정을 도와주는 투표앱", "https://github.com/yougjinc/Pick"),
            RepositoryInfo(
                "NaengjanGo",
                "냉장고 식자제 유통기한 관리 서비스",
                "https://github.com/youngjinc/NaengjanGo"
            ),
        )
    }

    companion object {
        private const val TAG = "GithubProfileRemoteDataSource"
    }
}