package com.smeup.rpgweb.db

import org.junit.Test
import java.sql.DriverManager

class DBTest {
    @Test
    fun dbConnection() {
        DriverManager.getConnection("jdbc:hsqldb:file:testDB/testDB", "SA", "")
    }
}
