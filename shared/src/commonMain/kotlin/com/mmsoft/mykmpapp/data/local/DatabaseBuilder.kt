package com.mmsoft.mykmpapp.data.local

import androidx.room.RoomDatabase

expect fun getRoomDatabaseBuilder(context: Any): RoomDatabase.Builder<AppDatabase>