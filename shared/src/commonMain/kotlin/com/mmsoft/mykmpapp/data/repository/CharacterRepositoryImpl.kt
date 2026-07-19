package com.mmsoft.mykmpapp.data.repository

import com.mmsoft.mykmpapp.data.local.CharacterDao
import com.mmsoft.mykmpapp.data.local.CharacterEntity
import com.mmsoft.mykmpapp.data.mapper.toDomain
import com.mmsoft.mykmpapp.data.model.CharacterDto
import com.mmsoft.mykmpapp.data.model.CharacterResponse
import com.mmsoft.mykmpapp.data.model.toDomain
import com.mmsoft.mykmpapp.data.safeApiCall
import com.mmsoft.mykmpapp.domain.model.CharacterDomainModel
import com.mmsoft.mykmpapp.domain.repository.CharacterRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val client: HttpClient,
    private val characterDao: CharacterDao
) : CharacterRepository {
    //Get characters from API
    override suspend fun getCharacters(): Result<List<CharacterDomainModel>> {
        return safeApiCall {
            val response = client.get("https://rickandmortyapi.com/api/character").body<CharacterResponse>()
            response.results.map { dto -> dto.toDomain() }
        }
    }

    override suspend fun getCharacterById(id: Int): Result<CharacterDomainModel> {
        return safeApiCall {
            val response = client.get("https://rickandmortyapi.com/api/character/$id").body<CharacterDto>()
            response.toDomain()
        }
    }

    //Get characters from DB
    override fun getAllCharacters(): Flow<List<CharacterDomainModel>> {
        return characterDao.getAllCharacters().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertDummyCharacter() {
        val dummy = CharacterEntity(
            id = 1,
            name = "Rick Sanchez (DB Dummy)",
            status = "Alive",
            species = "Human",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )

        characterDao.insertCharacter(dummy)
    }
}