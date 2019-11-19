package com.smeup.rpgweb

import com.google.gson.GsonBuilder
import com.smeup.rpgparser.execution.CommandLineProgram
import com.smeup.rpgparser.jvminterop.JavaSystemInterface
import com.smeup.rpgparser.rgpinterop.DirRpgProgramFinder
import com.smeup.rpgparser.rgpinterop.RpgSystem
import com.smeup.rpgweb.db.dbsqlInterface
import io.javalin.Javalin
import io.javalin.http.Context
import java.io.File

const val SRC_DIR = "srcRPG"

fun main() {
    Javalin.create { config ->
        config.defaultContentType = "application/json"
        config.enableCorsForAllOrigins()
    }
        .get("/", helloWorld)
        .get("/run/:pgmName", rpgHandler)
        .get("/employees", allEmployees)
        .get("/employees/dept/:deptCode", employeesByDept)
        .exception(Exception::class.java) { e, ctx ->
            ctx.status(500)
            ctx.jsonResponse(e.toString())
            e.printStackTrace()
        }
        .start(7000)
    RpgSystem.db = dbsqlInterface()
    val x = File(".")
    RpgSystem.addProgramFinders(listOf(DirRpgProgramFinder(File(SRC_DIR))))
}

val helloWorld = fun(ctx: Context) {
    ctx.jsonResponse("Hello world!")
}

val rpgHandler = fun(ctx: Context) {
    println("Running rpgHandler...")
    ctx.jsonResponse(rpgExecution(ctx.pathParam("pgmName"), ctx.extractNumberedParameters()))
    println("...rpgHandler done!")
}

val allEmployees = fun(ctx: Context) {
    println("Running allEmployees...")
    ctx.jsonResponse(JSonTable(rpgExecution("ALLEMP", listOf(ctx.pathParam("deptCode")))))
    println("...allEmployees done!")
}

val employeesByDept = fun(ctx: Context) {
    println("Running employeesByDept...")
    ctx.jsonResponse(JSonTable(rpgExecution("EMPBYDEPT", listOf(ctx.pathParam("deptCode")))))
    println("...employeesByDept done!")
}

private fun rpgExecution(pgmName: String, parms: List<String>): List<String> {
    val javaSystemInterface = JavaSystemInterface(System.out, RpgSystem::getProgram, RpgSystem.db)
    val pgm = CommandLineProgram(pgmName, javaSystemInterface)
    pgm.singleCall(parms)
    println("Call of $pgmName completed")
    return javaSystemInterface.consoleOutput
}

fun Context.extractNumberedParameters(): List<String> =
    (1..9).mapNotNull { this.queryParam("p$it") }

fun Context.jsonResponse(obj: Any) {
    val gSon = GsonBuilder().setPrettyPrinting().create()
    this.result(gSon.toJson(obj))
}
