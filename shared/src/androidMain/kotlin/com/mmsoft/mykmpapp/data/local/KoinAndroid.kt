package com.mmsoft.mykmpapp.data.local

import android.content.Context
import com.mmsoft.mykmpapp.di.databaseModule
import com.mmsoft.mykmpapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val nativeModule = module {
    single { getRoomDatabaseBuilder(androidContext()) }
}

fun startKoin(context: Context) {
    initKoin {
        androidContext(context)
        modules(nativeModule, databaseModule)
    }
}

