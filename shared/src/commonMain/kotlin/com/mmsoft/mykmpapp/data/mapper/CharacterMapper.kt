package com.mmsoft.mykmpapp.data.mapper

import com.mmsoft.mykmpapp.data.local.CharacterEntity
import com.mmsoft.mykmpapp.domain.model.CharacterDomainModel

fun CharacterEntity.toDomain(): CharacterDomainModel {
    return CharacterDomainModel(id, name, status, species, imageUrl)
}

fun CharacterDomainModel.toEntity(): CharacterEntity {
    return CharacterEntity(id, name, status, species, imageUrl)
}