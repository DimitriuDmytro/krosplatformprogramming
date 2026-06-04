package com.example.lb6

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform