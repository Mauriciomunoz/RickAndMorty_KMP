package com.mmsoft.mykmpapp.domain.repository

import com.mmsoft.mykmpapp.domain.UserDomainModel

interface UserRepository {
    suspend fun getUserProfile(): Result<UserDomainModel>
}