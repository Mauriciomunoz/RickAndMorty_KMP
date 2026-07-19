package com.mmsoft.mykmpapp.di


import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.mmsoft.mykmpapp.data.local.AppDatabase
import com.mmsoft.mykmpapp.data.local.CharacterDao
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single { httpClient }

    //Singleton creation
    single<UserRepository> { UserRepositoryImpl(get()) }

    //Used modern syntax for dependency injection
    //single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    singleOf(::CharacterRepositoryImpl) bind CharacterRepository::class

    //viewModel requires get() for each parameter required
    //viewModelOf moderns syntax for creating viewmodels. Same for singleOf and factoryOf
    viewModel { UserViewModel(get()) }
    viewModelOf(::CharacterListViewModel)
    viewModel { CharacterDetailViewModel(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}

fun initKoin() {
    initKoin(config = null)
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

val databaseModule = module {
    //Singleton database
    single<AppDatabase> {
        val builder = get<RoomDatabase.Builder<AppDatabase>>()
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    //Extract the dao to easier injection
    single<CharacterDao> {
        val database = get<AppDatabase>()
        database.characterDao()
    }
}