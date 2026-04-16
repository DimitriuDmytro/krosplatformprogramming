package com.example.laba1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform