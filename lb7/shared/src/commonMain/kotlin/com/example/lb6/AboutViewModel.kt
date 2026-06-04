package com.example.lb6

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AboutViewModel : ViewModel() {
    // Вказуємо тип <String> явно в кутових дужках
    private val _platformName = MutableStateFlow<String>(getPlatform().name)
    val platformName: StateFlow<String> = _platformName
}