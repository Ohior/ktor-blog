package com.example

import com.example.dao.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureTemplating()
        configureRouting()
    }.start(wait = true)
}
