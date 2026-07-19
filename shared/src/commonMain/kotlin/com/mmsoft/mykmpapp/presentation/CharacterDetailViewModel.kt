package com.mmsoft.mykmpapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmsoft.mykmpapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    fun loadCharacterDetail(id: Int){
        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading

            repository.getCharacterById(id)
                .onSuccess { character ->
                    _uiState.value = CharacterUiState.Success(listOf(character))
                }
                .onFailure { exception ->
                    _uiState.value = CharacterUiState.Error(exception.message ?: "Unknown error")
                }
        }
    }

}