package com.mmsoft.mykmpapp.domain.repository

import com.mmsoft.mykmpapp.domain.model.UserDomainModel

interface UserRepository {
    suspend fun getUserProfile(): Result<UserDomainModel>
}