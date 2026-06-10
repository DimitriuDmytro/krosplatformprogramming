package com.example.lb9

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform