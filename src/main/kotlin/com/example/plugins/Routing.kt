package com.example.plugins

import com.example.dao.DAOFacadeImpl
import com.example.dao.dao
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.*

fun Application.configureRouting() {
    routing {
        get("/"){
            call.respondRedirect("articles")
        }
        route("articles"){
            get {
//                The call.respond function accepts the FreeMarkerContent object that represents content
//                to be sent to the client. In our case, the FreeMarkerContent constructor accepts two parameters:
//                * template is a name of a template loaded by the FreeMarker plugin.
//                * model is a data model to be passed during template rendering. In our case, we pass an already
//                created list of articles in the articles' template variable.
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to dao.allArticles())))
            }
            get("new") {
                // Show a page with fields for creating a new article
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                // Save an article
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val article = dao.addNewArticle(title, body)
                call.respondRedirect("/articles/${article?.id}")
            }
            get("{id}") {
                // Show an article with a specific id
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("show.ftl", mapOf("article" to dao.article(id))))
            }
            get("{id}/edit") {
                // Show a page with fields for editing an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to dao.article(id))))
            }
            post("{id}") {
                // Update or delete an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        dao.editArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        dao.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
//        add static resources
        static("/static"){
            resources("files")
        }
    }
}
