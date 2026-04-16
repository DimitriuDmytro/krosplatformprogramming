package com.example.laba1

import kotlinx.datetime.*

// Інтерфейс для роботи з часом
interface DateTimeInterface {
    fun getCurrentDateTime(): String
}

// Клас, що реалізує інтерфейс
class DateTimeHelper : DateTimeInterface {
    override fun getCurrentDateTime(): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return "${now.dayOfMonth}.${now.monthNumber}.${now.year} ${now.hour}:${now.minute.toString().padStart(2, '0')}"
    }
}