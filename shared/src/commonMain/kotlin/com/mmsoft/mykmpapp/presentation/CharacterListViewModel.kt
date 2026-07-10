package com.mmsoft.mykmpapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmsoft.mykmpapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters(){
        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading

            repository.getCharacters()
                .onSuccess { characterList ->
                    _uiState.value = CharacterUiState.Success(characterList)
                }
                .onFailure { exception ->
                    _uiState.value = CharacterUiState.Error(exception.message ?: "Error desconocido")
                }
        }
    }

}