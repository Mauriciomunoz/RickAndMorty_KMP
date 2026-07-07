package com.mmsoft.mykmpapp.di


import com.mmsoft.mykmpapp.data.repository.UserRepositoryImpl
import com.mmsoft.mykmpapp.presentation.UserViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    //Singleton creation
    singleOf(::UserRepositoryImpl)

    factory { UserViewModel(get()) }

}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}