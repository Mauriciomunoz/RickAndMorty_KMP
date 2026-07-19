package com.mmsoft.mykmpapp.data.local

import com.mmsoft.mykmpapp.di.databaseModule
import com.mmsoft.mykmpapp.di.initKoin
import org.koin.dsl.module

val nativeModule = module {
    single { getRoomDatabaseBuilder(Unit) }
}

fun startKoin() {
    initKoin {
        modules(nativeModule, databaseModule)
    }
}