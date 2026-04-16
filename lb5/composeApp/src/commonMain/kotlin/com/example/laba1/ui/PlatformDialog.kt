package com.example.laba1.ui

import androidx.compose.runtime.Composable

@Composable
expect fun TimeZoneDialogWrapper(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
)