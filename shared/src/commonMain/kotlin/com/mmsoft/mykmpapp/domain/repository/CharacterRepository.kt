package com.mmsoft.mykmpapp.domain.repository

import com.mmsoft.mykmpapp.domain.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<CharacterDomainModel>>

    suspend fun getCharacterById(id: Int): Result<CharacterDomainModel>

    fun getAllCharacters(): Flow<List<CharacterDomainModel>>
    suspend fun insertDummyCharacter()
}