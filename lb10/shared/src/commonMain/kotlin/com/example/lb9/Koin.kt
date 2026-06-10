package com.example.lb9

import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.core.module.Module

// Вставте цей модуль у той файл, де у вас ініціалізується Koin
val appModule: Module = module {
    // Явно вказуємо тип Settings, щоб уникнути конфлікту
    single<Settings> { com.russhwolf.settings.Settings() }

    single { SystemRepository(get<Settings>()) }
    factory { AboutViewModel(get<SystemRepository>()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}