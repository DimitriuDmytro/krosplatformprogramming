package com.example.laba1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource

// ЦІ ІМПОРТИ ОБОВ'ЯЗКОВІ (Завдання 1) [cite: 3]
import io.github.aakira.napier.Napier
import io.github.aakira.napier.DebugAntilog

import laba1.composeapp.generated.resources.Res
import laba1.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    // 1. Ініціалізація логування (завдання 1) [cite: 3, 6]
    LaunchedEffect(Unit) {
        Napier.base(DebugAntilog())
        Napier.d("Додаток запущено", tag = "LAB2")
    }

    // 2. Використання класу для дати та часу (завдання 3) [cite: 5]
    val dateTimeHelper = remember { DateTimeHelper() }
    val currentTime = dateTimeHelper.getCurrentDateTime()

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 3. Вивід поточної дати на екран (завдання 4) [cite: 6]
            Text(
                text = "Поточний час: $currentTime",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(onClick = {
                showContent = !showContent
                // 4. Логування події натискання (завдання 4) [cite: 6]
                Napier.i("Кнопку натиснуто. Стан: $showContent", tag = "LAB2")
            }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}