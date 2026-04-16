package com.example.laba1.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.dp

@Composable
actual fun TimeZoneDialogWrapper(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    // Відкриваємо справжнє вікно Desktop (п. 2.а)
    Window(
        onCloseRequest = onCloseRequest,
        title = title,
        state = rememberWindowState(width = 400.dp, height = 500.dp)
    ) {
        content()
    }
}