package com.example.laba1.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
actual fun TimeZoneDialogWrapper(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    // Для Android використовуємо стандартний Dialog (вимога п. 1 лаби) [cite: 3]
    Dialog(onDismissRequest = onCloseRequest) {
        content()
    }
}