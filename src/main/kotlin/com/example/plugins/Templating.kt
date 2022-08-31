package com.example.plugins

import freemarker.cache.*
import freemarker.core.HTMLOutputFormat
import io.ktor.freemarker.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureTemplating() {
    install(FreeMarker) {
//        The outputFormat setting helps convert control characters provided by the user to
//        their corresponding HTML entities. This ensures that when one of our journal
//        entries contains a String like <b>Hello</b>, it is actually printed as <b>Hello</b>
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }
    routing {
    }
}
