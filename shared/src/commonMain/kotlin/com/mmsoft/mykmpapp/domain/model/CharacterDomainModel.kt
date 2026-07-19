package com.mmsoft.mykmpapp.domain.model

data class CharacterDomainModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val imageUrl: String
)