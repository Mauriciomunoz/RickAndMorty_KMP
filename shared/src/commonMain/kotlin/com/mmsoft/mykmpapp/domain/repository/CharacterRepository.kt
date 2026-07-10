package com.mmsoft.mykmpapp.domain.repository

import com.mmsoft.mykmpapp.domain.CharacterDomainModel

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<CharacterDomainModel>>
}