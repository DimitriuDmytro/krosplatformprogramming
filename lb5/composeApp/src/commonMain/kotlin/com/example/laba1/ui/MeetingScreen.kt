package com.example.laba1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.* // Використовуємо Material3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MeetingScreen(
    onSearchClick: () -> Unit // Викликається для показу діалогу з результатом [cite: 6, 8]
) {
    // Стан для вибору годин
    var startHour by remember { mutableStateOf(9f) }
    var endHour by remember { mutableStateOf(17f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Знайти час для зустрічі",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 1. Кастомні елементи вибору інтервалу [cite: 6]
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Початок інтервалу: ${startHour.toInt()}:00")
                Slider(
                    value = startHour,
                    onValueChange = { startHour = it },
                    valueRange = 0f..23f
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Кінець інтервалу: ${endHour.toInt()}:00")
                Slider(
                    value = endHour,
                    onValueChange = { endHour = it },
                    valueRange = 0f..23f
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Кнопка пошуку [cite: 6]
        Button(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("ПОШУК")
        }
    }
}