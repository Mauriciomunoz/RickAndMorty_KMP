package com.mmsoft.mykmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform