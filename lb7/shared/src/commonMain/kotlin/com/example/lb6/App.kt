package com.example.lb6

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(viewModel: AboutViewModel = koinViewModel()) {
    // Підписуємось на стан з ViewModel
    val platformName by viewModel.platformName.collectAsState()
    var showContent by remember { mutableStateOf(false) }

    AppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                // Використовуємо дані, отримані через ViewModel
                Text("Compose: Hello, $platformName!")
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}