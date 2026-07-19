package com.mmsoft.mykmpapp.data.model

import com.mmsoft.mykmpapp.domain.model.CharacterDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val results: List<CharacterDto>
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)

fun CharacterDto.toDomain(): CharacterDomainModel {
    return CharacterDomainModel(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        imageUrl = this.image
    )
}