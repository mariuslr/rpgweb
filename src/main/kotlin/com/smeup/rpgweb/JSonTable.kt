package com.smeup.rpgweb

class JSonTable(rpgDisplay: List<String>) {
    val columns = mutableListOf<Column>()
    val rows = mutableListOf<Row>()

    init {
        fun findColumns(rpgDisplay: List<String>): Int {
            val columnDefinitions = rpgDisplay.takeWhile { it != "_##_ROWS" }
            require(columnDefinitions.size % 2 == 0) {
                "Wrong column definition: name and title are required for all columns"
            }
            columns.addAll(columnDefinitions.chunked(2).map { Column(it[0], it[1]) })
            return columnDefinitions.size + 1
        }

        fun findRows(rowsElements: List<String>) {
            var fieldsAccumulator = LinkedHashMap<String, AppObj>()
            rowsElements.forEach {
                if (it == "_##_ENDROW") {
                    rows.add(Row(fieldsAccumulator))
                    fieldsAccumulator = LinkedHashMap<String, AppObj>()
                } else {
                    val tokens = it.split("_##_")
                    val fieldName = tokens[0]
                    val value = tokens[1]
                    fieldsAccumulator.put(fieldName, AppObj(value))
                }
            }
        }
        if (rpgDisplay.isEmpty()) {
            println("Empty display")
        } else {
            val firstRowIndex = findColumns(rpgDisplay)
            if (firstRowIndex <= rpgDisplay.size) {
                findRows(rpgDisplay.subList(firstRowIndex, rpgDisplay.size))
            }
        }
    }
}

data class Column(val name: String, val title: String)

data class Row(val cells: Map<String, AppObj>)

data class AppObj(val value: String, val obj: ObjType = emptyObjType)

data class ObjType(val t: String, val p: String, val k: String)

val emptyObjType = ObjType("", "", "")
