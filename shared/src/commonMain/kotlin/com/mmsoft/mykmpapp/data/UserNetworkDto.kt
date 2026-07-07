package com.mmsoft.mykmpapp.data

import com.mmsoft.mykmpapp.domain.UserDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserNetworkDto(
    val firstName: String,
    val lastName: String,

    @SerialName("user_id")
    val networkId: Int,

    @SerialName("emai")
    val networkEmail: String
)

fun UserNetworkDto.toDomain(): UserDomainModel {
    return UserDomainModel(
        id = this.networkId,
        name = "${this.firstName} ${this.lastName}",
        email = this.networkEmail
    )
}