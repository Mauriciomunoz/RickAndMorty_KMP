package com.mmsoft.mykmpapp.presentation

import com.mmsoft.mykmpapp.domain.model.CharacterDomainModel

sealed interface CharacterUiState {
    object Loading : CharacterUiState
    data class Success(val characters: List<CharacterDomainModel>) : CharacterUiState
    data class Error(val message: String) : CharacterUiState
}