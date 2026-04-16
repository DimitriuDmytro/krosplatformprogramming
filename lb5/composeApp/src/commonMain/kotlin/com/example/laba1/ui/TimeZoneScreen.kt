package com.example.laba1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.* // Використовуємо Material3
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeZoneScreen(
    onShowDialog: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            // Використовуємо кастомну кнопку, щоб уникнути помилок з Icons
            FloatingActionButton(
                onClick = onShowDialog,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                // Замість іконки використовуємо текст, щоб не було помилок імпорту
                Text(
                    text = "+",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Мій час",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // 1. Карточка користувача (згідно з п. 1.а лаби)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Київ, Україна",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "14:30", // Тут пізніше будуть дані з DateTimeHelper
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "GMT +3",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

            Text(
                text = "Обрані пояси",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // 2. Список обраних поясів (п. 1.а лаби)
            val demoTimeZones = listOf("London", "New York", "Tokyo")

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(demoTimeZones) { city ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = city, style = MaterialTheme.typography.bodyLarge)
                            Text(
                                text = "12:30",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}