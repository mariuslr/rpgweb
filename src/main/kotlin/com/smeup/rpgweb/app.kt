package com.smeup.rpgweb

import com.google.gson.GsonBuilder
import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.http.Handler
import java.io.File

fun main(args: Array<String>) {
    val app = Javalin.create{ config ->
        config.defaultContentType = "application/json"
        config.enableCorsForAllOrigins()
    }.start(7000)
    app.get("/", HelloWorldHandler)
    app.get("/run", RunRPGHandler)
}

object RunRPGHandler : Handler {
    override fun handle(ctx: Context) {
        println(File(".").absolutePath)
    }
}

object HelloWorldHandler : Handler {
    override fun handle(ctx: Context) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        ctx.result(gson.toJson("Hello world!"))
    }
}
