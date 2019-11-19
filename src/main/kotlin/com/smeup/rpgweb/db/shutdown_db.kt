@file:JvmName("ShutDownDB")

package com.smeup.rpgweb.db

import java.lang.Exception
import java.lang.NullPointerException

fun main() {
    println("Shutdown of DB engine...")
    val initialSQL = listOf(shutdownDB())
    val dbInterface = dbsqlInterface()
    try {
        dbInterface.execute(initialSQL)
    } catch (e: NullPointerException) {
        // Nothing
    }
    println("...done!")
}

fun shutdownDB(): String = "SHUTDOWN"
