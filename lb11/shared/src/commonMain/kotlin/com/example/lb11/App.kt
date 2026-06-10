package com.example.lb11

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import network.NetworkResult

@Composable
fun App() {
    MaterialTheme {
        val viewModel: MainViewModel = koinInject()

        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { viewModel.performRequest("GET") }) { Text("GET") }
                Button(onClick = { viewModel.performRequest("POST") }) { Text("POST") }
                Button(onClick = { viewModel.performRequest("PUT") }) { Text("PUT") }
                Button(onClick = { viewModel.performRequest("DELETE") }) { Text("DELETE") }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Крок 6b: Прогрес бар
            if (uiState is NetworkResult.Loading) {
                CircularProgressIndicator()
            }

            when (val result = uiState) {
                is NetworkResult.Success<*> -> {
                    Text(
                        text = "Успіх: ${result.data}",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                is NetworkResult.Error -> {
                    Text(
                        text = "Помилка: ${result.message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    Text("Очікування запиту...")
                }
            }
        }
    }
}