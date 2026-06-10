package com.example.lb11

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform