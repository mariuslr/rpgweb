@file:JvmName("CreateDB")

package com.smeup.rpgweb.db

import com.smeup.rpgparser.db.sql.CONVENTIONAL_INDEX_SUFFIX
import com.smeup.rpgparser.db.sql.DBConfiguration
import com.smeup.rpgparser.db.sql.DBSQLInterface

fun main() {
    println("Setup of the testDB/testDB...")
    val initialSQL =
        listOf(
            createEMPLOYEE(),
            createXEMP2(),
            createXEMP2Index(),
            setTableTextType(),
            insertRecords())
    val dbInterface = dbsqlInterface()
    dbInterface.execute(initialSQL)
    println("...done!")
}

fun dbsqlInterface(): DBSQLInterface {
    val dbInterface = DBSQLInterface(DBConfiguration("jdbc:hsqldb:hsql://localhost/testDB", "SA"))
    dbInterface.setSQLLog(true)
    return dbInterface
}

private fun createEMPLOYEE() =
    """
    CREATE TEXT TABLE EMPLOYEE ( 
	EMPNO CHAR(6) DEFAULT '' NOT NULL , 
	FIRSTNME CHAR(12) DEFAULT '' NOT NULL , 
	MIDINIT CHAR(1) DEFAULT '' NOT NULL , 
	LASTNAME CHAR(15) DEFAULT '' NOT NULL , 
	WORKDEPT CHAR(3) DEFAULT '' NOT NULL, 
    PHONENO     CHAR(4)                 ,
    JOB         CHAR(8)                 ,
    SEX         CHAR(1)                 ,
    SALARY      DECIMAL(9,2)            ,
	PRIMARY KEY( EMPNO ) )   
        """.trimIndent()

private fun setTableTextType() =
    """
         SET TABLE EMPLOYEE SOURCE "employee.csv;fs=\semi;encoding=UTF-8"
    """.trimIndent()

private fun createXEMP2() = "CREATE VIEW XEMP2 AS SELECT * FROM EMPLOYEE ORDER BY WORKDEPT"

private fun createXEMP2Index() = "CREATE INDEX XEMP2$CONVENTIONAL_INDEX_SUFFIX ON EMPLOYEE (WORKDEPT ASC)"

private fun insertRecords() =
    """
INSERT INTO EMPLOYEE (EMPNO, FIRSTNME, MIDINIT, LASTNAME, WORKDEPT, PHONENO, JOB, SEX, SALARY) VALUES 
('000010','CHRISTINE','I','HAAS','A00','3978','PRES    ','F',52750.00),
('000020','MICHAEL','L','THOMPSON','B01','3476','MANAGER ','M',41250.00),
('000030','SALLY','A','KWAN','C01','4738','MANAGER ','F',38250.00),
('000050','JOHN','B','GEYER','E01','6789','MANAGER ','M',40175.00),
('000060','IRVING','F','STERN','D11','6423','MANAGER ','M',32250.00),
('000070','EVA','D','PULASKI','D21','7831','MANAGER ','F',36170.00),
('000090','EILEEN','W','HENDERSON','E11','5498','MANAGER ','F',29750.00),
('000100','THEODORE','Q','SPENSER','E21','0972','MANAGER ','M',26150.00),
('000110','VINCENZO','G','LUCCHESSI','A00','3490','SALESREP','M',46500.00),
('000120','SEAN',' ','O''CONNELL','A00','2167','CLERK   ','M',29250.00),
('000130','DELORES','M','QUINTANA','C01','4578','ANALYST ','F',23800.00),
('000140','HEATHER','A','NICHOLLS','C01','1793','ANALYST ','F',28420.00),
('000150','BRUCE',' ','ADAMSON','D11','4510','DESIGNER','M',25280.00),
('000160','ELIZABETH','R','PIANKA','D11','3782','DESIGNER','F',22250.00),
('000170','MASATOSHI','J','YOSHIMURA','D11','2890','DESIGNER','M',24680.00),
('000180','MARILYN','S','SCOUTTEN','D11','1682','DESIGNER','F',21340.00),
('000190','JAMES','H','WALKER','D11','2986','DESIGNER','M',20450.00),
('000200','DAVID',' ','BROWN','D11','4501','DESIGNER','M',27740.00),
('000210','WILLIAM','T','JONES','D11','0942','DESIGNER','M',18270.00),
('000220','JENNIFER','K','LUTZ','D11','0672','DESIGNER','F',29840.00),
('000230','JAMES','J','JEFFERSON','D21','2094','CLERK   ','M',22180.00),
('000240','SALVATORE','M','MARINO','D21','3780','CLERK   ','M',28760.00),
('000250','DANIEL','S','SMITH','D21','0961','CLERK   ','M',19180.00),
('000260','SYBIL','P','JOHNSON','D21','8953','CLERK   ','F',17250.00),
('000270','MARIA','L','PEREZ','D21','9001','CLERK   ','F',27380.00),
('000280','ETHEL','R','SCHNEIDER','E11','8997','OPERATOR','F',26250.00),
('000290','JOHN','R','PARKER','E11','4502','OPERATOR','M',15340.00),
('000300','PHILIP','X','SMITH','E11','2095','OPERATOR','M',17750.00),
('000310','MAUDE','F','SETRIGHT','E11','3332','OPERATOR','F',15900.00),
('000320','RAMLAL','V','MEHTA','E21','9990','FIELDREP','M',19950.00),
('000330','WING',' ','LEE','E21','2103','FIELDREP','M',25370.00),
('000340','JASON','R','GOUNOT','E21','5698','FIELDREP','M',23840.00),
('200010','DIAN','J','HEMMINGER','A00','3978','SALESREP','F',46500.00),
('200120','GREG',' ','ORLANDO','A00','2167','CLERK   ','M',29250.00),
('200140','KIM','N','NATZ','C01','1793','ANALYST ','F',28420.00),
('200170','KIYOSHI',' ','YAMAMOTO','D11','2890','DESIGNER','M',24680.00),
('200220','REBA','K','JOHN','D11','0672','DESIGNER','F',29840.00),
('200240','ROBERT','M','MONTEVERDE','D21','3780','CLERK   ','M',28760.00),
('200280','EILEEN','R','SCHWARTZ','E11','8997','OPERATOR','F',26250.00),
('200310','MICHELLE','F','SPRINGER','E11','3332','OPERATOR','F',15900.00),
('200330','HELENA',' ','WONG','E21','2103','FIELDREP','F',25370.00),
('200340','ROY','R','ALONZO','E21','5698','FIELDREP','M',23840.00)     
        """.trimIndent()
