package com.mmsoft.mykmpapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual fun getRoomDatabaseBuilder(context: Any): RoomDatabase.Builder<AppDatabase> {
    val androidContext = context as Context
    val dbFile = androidContext.getDatabasePath("rick_and_morty.db")
    return Room.databaseBuilder<AppDatabase>(
        context = androidContext.applicationContext,
        name = dbFile.absolutePath
    )
}