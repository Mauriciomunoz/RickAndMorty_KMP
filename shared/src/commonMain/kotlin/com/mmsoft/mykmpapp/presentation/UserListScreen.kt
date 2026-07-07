package com.mmsoft.mykmpapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmsoft.mykmpapp.presentation.UserViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Immutable
data class UserUiItem(
    val id: Int,
    val name: String,
    val email: String
)


@Composable
fun UserListRoute(
    viewModel: UserViewModel = koinViewModel(),
    modifier: Modifier = Modifier
){
    //If the app goes to the background, the data collect is paused
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    //Snackbar handler
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true){
        //The event is listened and trigger the snackbar
        viewModel.uiEvent.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when(val state = uiState){
            is UserUiState.Loading -> {
                CircularProgressIndicator()
            }
            is UserUiState.Success -> {
                UserListScreen(
                    state.users,
                    snackbarHostState
                )
            }
            is UserUiState.Error -> {
                Text(text = "An error has occurred: {${state.message}}")
            }
        }
    }
}

@Composable
fun UserListScreen(
    users: List<UserUiItem>,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
){

    //Remember the state of the list scroll
    val listState = rememberLazyListState()

    //Use derivedStateOf to damp the recomposition
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold (
        //Notification host is connected to the scaffold
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (showButton) {
                FloatingActionButton(onClick = { /* Scroll to top */ }) {
                    Text("^")
                }
            }
        }
    ) {
        paddingValues ->
            LazyColumn(
                state = listState,
                contentPadding = paddingValues,
                modifier = modifier
            ) {
                items(items = users, key = { user -> user.id }) { user ->
                    UserRow(user = user)
                }
            }
    }
}

@Composable
fun UserRow(user: UserUiItem) {
    Text(text = user.name)
}