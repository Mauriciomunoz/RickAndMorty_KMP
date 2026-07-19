package com.mmsoft.mykmpapp.data.repository

import com.mmsoft.mykmpapp.domain.model.UserDomainModel
import com.mmsoft.mykmpapp.domain.repository.UserRepository
import com.mmsoft.mykmpapp.data.model.UserDto
import com.mmsoft.mykmpapp.data.safeApiCall
import com.mmsoft.mykmpapp.data.model.toDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UserRepositoryImpl(private val client: HttpClient) : UserRepository {
    override suspend fun getUserProfile(): Result<UserDomainModel> {
        return safeApiCall {
            val response =
                client.get("https://jsonplaceholder.typicode.com/users/1").body<UserDto>()
            response.toDomain()
        }
    }
}