package com.mmsoft.mykmpapp.presentation

sealed interface UserUiState {
    object Loading: UserUiState
    data class Success(val users: List<UserUiItem>): UserUiState
    data class Error(val message: String): UserUiState
}