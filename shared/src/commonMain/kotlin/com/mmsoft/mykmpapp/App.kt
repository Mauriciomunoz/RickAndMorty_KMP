package com.mmsoft.mykmpapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.annotation.ExperimentalCoilApi
import com.mmsoft.mykmpapp.presentation.CharacterDetailRoute
import com.mmsoft.mykmpapp.presentation.CharacterListRoute

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        //Saves the id of the selected character
        var selectedCharacterId by remember { mutableStateOf<String?>(null) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if(selectedCharacterId == null) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Rick & Morty KMP") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        CharacterListRoute(
                            onCharacterClick = { id ->
                                selectedCharacterId = id
                            }
                        )
                    }
                }
            }else{
                CharacterDetailRoute(
                    characterId = selectedCharacterId!!,
                    onBackClick = {
                        selectedCharacterId = null
                    }
                )
            }
        }
    }
}