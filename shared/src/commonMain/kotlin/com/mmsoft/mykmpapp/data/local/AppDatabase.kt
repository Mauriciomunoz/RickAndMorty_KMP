package com.mmsoft.mykmpapp.data.local

import androidx.room.Database

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase {
    abstract fun userDao(): UserDao
}