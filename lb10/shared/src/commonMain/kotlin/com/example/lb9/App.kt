package com.example.lb9

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.coroutines.flow.StateFlow
import lb6.shared.generated.resources.Res
import lb6.shared.generated.resources.compose_multiplatform



@Composable
fun App(viewModel: AboutViewModel = koinViewModel<AboutViewModel>()) {
    // Підписка на StateFlow з ViewModel
    val platformName by viewModel.platformName.collectAsState()
    val openCount by viewModel.openCount.collectAsState()

    // Стан для відображення прихованого контенту
    var showContent by remember { mutableStateOf(false) }

    AppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Вивід даних з репозиторію
            Text("Привіт, $platformName!")
            Text("Додаток відкрито разів: $openCount")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            AnimatedVisibility(visible = showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null
                    )
                    Text("Compose: $greeting")
                }
            }
        }
    }
}





