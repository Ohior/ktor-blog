package com.example.models

import org.jetbrains.exposed.sql.*

//class Article
//private constructor(val id:Int, var title:String, var body:String){
//    companion object{
////        a unique id is generated automatically using AtomicInteger -
////        a thread-safe data structure that ensures that two articles will never receive the same ID
//        private val idCounter = AtomicInteger()
//        fun newEntry(title: String, body: String) = Article(idCounter.getAndIncrement(), title, body)
//    }
//}

//val articles = mutableListOf<Article>(
//    Article.newEntry("The Drive To Develop", "Is what keeps me going"),
//)

data class Article(val id:Int, val title:String, val body:String)

object Articles : Table(){
    val id = integer("id").autoIncrement()
    val title = varchar(name = "title", length = 50)
    val body = varchar(name = "body", length = 1000)

    override val primaryKey = PrimaryKey(id)
}


