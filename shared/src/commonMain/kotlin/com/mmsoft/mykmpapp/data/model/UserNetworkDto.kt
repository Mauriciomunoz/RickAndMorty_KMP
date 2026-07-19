package com.mmsoft.mykmpapp.data.model

import com.mmsoft.mykmpapp.domain.model.UserDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: List<UserDto>
)


@Serializable
data class SingleUserResponse(
    val data: UserDto
)

@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    val name: String,
    val phone: String
)

fun UserDto.toDomain(): UserDomainModel {
    return UserDomainModel(
        id = this.id.toString(),
        name = this.name,
        email = this.email,
        phone = this.phone
    )
}