package com.mmsoft.mykmpapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmsoft.mykmpapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    //SharedFlow is used for event that not must repeat
    private val _uiEvent = MutableSharedFlow<String>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchUsers()
    }

    fun triggerSyncNotification(){
        viewModelScope.launch {
            //Emit an event, if no one is listen then the event is safe deleted
            _uiEvent.emit("Sync completed")
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading
            val result = withContext(Dispatchers.IO) {
                userRepository.getUserProfile()
            }

            result.onSuccess { domainModel ->
                val uiItems = listOf(UserUiItem(id = domainModel.id, name = domainModel.name, email = domainModel.email))
                _uiState.value = UserUiState.Success(uiItems)
            }
            .onFailure { exception ->
                _uiState.value = UserUiState.Error(exception.message ?: "Error desconocido")
            }
        }

    }
}