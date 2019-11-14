package com.smeup.rpgweb

import com.google.gson.GsonBuilder
import com.smeup.rpgparser.execution.getProgram
import com.smeup.rpgparser.jvminterop.JavaSystemInterface
import com.smeup.rpgparser.utils.StringOutputStream
import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.http.Handler
import java.io.PrintStream

fun main(args: Array<String>) {
    val app = Javalin.create{ config ->
        config.defaultContentType = "application/json"
        config.enableCorsForAllOrigins()
    }.start(7000)
    app.get("/", HelloWorldHandler)
    app.get("/run/:pgmName", RunRPGHandler)
}

object HelloWorldHandler : Handler {
    override fun handle(ctx: Context) {
        ctx.jsonResponse("Hello world!")
    }
}

object RunRPGHandler : Handler {
    override fun handle(ctx: Context) {
        ctx.jsonResponse(rpgExecution(ctx.pathParam("pgmName")))
    }
}

private fun rpgExecution(pgmName: String): List<String> {
    val logOutputStream = StringOutputStream()
    val printStream = PrintStream(logOutputStream)
    val javaSystemInterface = JavaSystemInterface(printStream)
    val pgm = getProgram("srcRPG/$pgmName", javaSystemInterface)
    pgm.singleCall(listOf("Franco"))
    return logOutputStream.toString().lines()
}

fun Context.jsonResponse(obj: Any) {
    val gson = GsonBuilder().setPrettyPrinting().create()
    this.result(gson.toJson(obj))
}

