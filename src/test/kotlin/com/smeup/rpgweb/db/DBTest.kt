package com.smeup.rpgweb.db

import org.junit.Test
import java.sql.DriverManager



class DBTest {
    @Test
    fun dbCreation() {
        var c = DriverManager.getConnection("jdbc:hsqldb:file:testDB/testDB", "SA", "")
    }
}
