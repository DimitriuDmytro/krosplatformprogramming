package com.example.laba1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 1. Діалог вибору часових зон (згідно з п. 2 лаби)
@Composable
fun TimeZoneSelectionDialog(
    onDismiss: () -> Unit,
    allTimeZones: List<String> // Список усіх доступних зон з DateTimeHelper
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Виберіть часові пояси") },
        text = {
            Box(modifier = Modifier.height(300.dp)) {
                LazyColumn {
                    items(allTimeZones) { zone ->
                        Row(modifier = Modifier.padding(vertical = 8.dp)) {
                            // Тут можна додати Checkbox для вибору
                            Text(text = zone)
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("OK") }
        }
    )
}

// 2. Діалог відображення результату пошуку (згідно з п. 3 лаби)
@Composable
fun SearchResultDialog(
    onDismiss: () -> Unit,
    searchResult: String // Результат обчислень
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Результат пошуку") },
        text = {
            Text(text = searchResult)
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Закрити") }
        }
    )
}