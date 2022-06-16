package org.sopt.soptseminar.data.models.db

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun saveUserInfo(user: LoginUserInfo)

    @Query("DELETE FROM user_table")
    suspend fun deleteUserInfo()

    @Query("SELECT * FROM user_table")
    fun getUserInfo(): LoginUserInfo?
}