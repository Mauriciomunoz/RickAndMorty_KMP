package com.mmsoft.mykmpapp.data.repository

import com.mmsoft.mykmpapp.domain.UserDomainModel
import com.mmsoft.mykmpapp.domain.repository.UserRepository
import com.mmsoft.mykmpapp.data.ApiClient
import com.mmsoft.mykmpapp.data.UserNetworkDto
import com.mmsoft.mykmpapp.data.safeApiCall
import com.mmsoft.mykmpapp.data.toDomain
import io.ktor.client.call.body
import io.ktor.client.request.get

class UserRepositoryImpl : UserRepository {
    override suspend fun getUserProfile(): Result<UserDomainModel> {
        return safeApiCall {
            val dtoprofile =
                ApiClient.client.get("https://api.example.com/user/profile").body<UserNetworkDto>()
            dtoprofile.toDomain()
        }
    }
}