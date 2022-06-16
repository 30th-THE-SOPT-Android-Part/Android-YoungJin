package org.sopt.soptseminar.data.models.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginUserInfo::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}