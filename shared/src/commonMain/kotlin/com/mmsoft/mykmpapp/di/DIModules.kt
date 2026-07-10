package com.mmsoft.mykmpapp.di


import com.mmsoft.mykmpapp.data.repository.CharacterRepositoryImpl
import com.mmsoft.mykmpapp.data.repository.UserRepositoryImpl
import com.mmsoft.mykmpapp.domain.repository.CharacterRepository
import com.mmsoft.mykmpapp.domain.repository.UserRepository
import com.mmsoft.mykmpapp.presentation.CharacterDetailViewModel
import com.mmsoft.mykmpapp.presentation.CharacterListViewModel
import com.mmsoft.mykmpapp.presentation.UserViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {

    single { httpClient }
    //Singleton creation
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }

    factory { UserViewModel(get()) }
    factory { CharacterListViewModel(get()) }
    factory { CharacterDetailViewModel(get()) }

}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}

val httpClient = HttpClient {
    //install the pluging to allow read and map JSON
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true // If the API sends unnecessary fields they are ignored
            prettyPrint = true
            isLenient = true
        })
    }
}