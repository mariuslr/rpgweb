package com.smeup.rpgweb

import com.google.gson.GsonBuilder
import com.smeup.rpgparser.execution.getProgram
import com.smeup.rpgparser.jvminterop.JavaSystemInterface
import com.smeup.rpgparser.utils.StringOutputStream
import io.javalin.Javalin
import io.javalin.http.Context
import java.io.PrintStream
import java.lang.Exception

fun main(args: Array<String>) {
    Javalin.create { config ->
        config.defaultContentType = "application/json"
        config.enableCorsForAllOrigins()
    }
        .get("/", helloWorld)
        .get("/run/:pgmName", rpgHandler)
        .exception(Exception::class.java) {e, ctx ->
            ctx.status(500)
            ctx.jsonResponse(e.toString())
        }
        .start(7000)
}

val helloWorld = fun(ctx: Context) {
    ctx.jsonResponse("Hello world!")
}

val rpgHandler = fun(ctx: Context) {
    ctx.jsonResponse(rpgExecution(ctx.pathParam("pgmName"), ctx.extractNumberedParameters()))
}

private fun rpgExecution(pgmName: String, parms: List<String>): List<String> {
    val logOutputStream = StringOutputStream()
    val javaSystemInterface = JavaSystemInterface(PrintStream(logOutputStream))
    val pgm = getProgram("srcRPG/$pgmName", javaSystemInterface)
    pgm.singleCall(parms)
    return logOutputStream.toString().lines().dropLast(1)
}

fun Context.extractNumberedParameters(): List<String> =
    (1..9).mapNotNull { this.queryParam("p$it") }

fun Context.jsonResponse(obj: Any) {
    val gSon = GsonBuilder().setPrettyPrinting().create()
    this.result(gSon.toJson(obj))
}
