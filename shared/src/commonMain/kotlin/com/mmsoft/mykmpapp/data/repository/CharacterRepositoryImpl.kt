package com.mmsoft.mykmpapp.data.repository

import com.mmsoft.mykmpapp.data.model.CharacterDto
import com.mmsoft.mykmpapp.data.model.CharacterResponse
import com.mmsoft.mykmpapp.data.model.toDomain
import com.mmsoft.mykmpapp.data.safeApiCall
import com.mmsoft.mykmpapp.domain.CharacterDomainModel
import com.mmsoft.mykmpapp.domain.repository.CharacterRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val client: HttpClient
) : CharacterRepository {
    override suspend fun getCharacters(): Result<List<CharacterDomainModel>> {
        return safeApiCall {
            val response = client.get("https://rickandmortyapi.com/api/character").body<CharacterResponse>()
            response.results.map { dto -> dto.toDomain() }
        }
    }

    override suspend fun getCharacterById(id: String): Result<CharacterDomainModel> {
        return safeApiCall {
            val response = client.get("https://rickandmortyapi.com/api/character/$id").body<CharacterDto>()
            response.toDomain()
        }
    }
}