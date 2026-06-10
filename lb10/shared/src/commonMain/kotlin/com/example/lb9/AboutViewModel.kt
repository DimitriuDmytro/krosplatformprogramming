package com.example.lb9

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AboutViewModel(private val repository: SystemRepository) : ViewModel() {

    // Назва платформи
    private val _platformName = MutableStateFlow(repository.getPlatformName())
    val platformName: StateFlow<String> = _platformName

    // Лічильник відкриттів: викликаємо метод репозиторію
    private val _openCount = MutableStateFlow(repository.incrementOpenCount())
    val openCount: StateFlow<Int> = _openCount
}