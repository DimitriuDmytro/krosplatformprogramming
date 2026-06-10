package com.example.lb9

import com.russhwolf.settings.Settings

class SystemRepository(private val settings: Settings) {

    // Повертає назву платформи (як було раніше)
    fun getPlatformName(): String = getPlatform().name

    // Збільшує лічильник і зберігає його
    fun incrementOpenCount(): Int {
        val count = settings.getInt("open_count", 0) + 1
        settings.putInt("open_count", count)
        return count
    }

    // Отримує поточне значення лічильника
    fun getOpenCount(): Int = settings.getInt("open_count", 0)
}