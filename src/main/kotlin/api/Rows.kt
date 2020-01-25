package api

import api.df.DataFrame
import api.df.MetaColumn
import api.df.Row

// Create Row methods
fun createRow(vararg headers: String): RowBuilder {
    TODO()
}

fun createRow(vararg columns: MetaColumn): RowBuilder {
    TODO()
}

fun createAndFillRow(data: Map<Any, Any>): DataFrame {
    TODO()
}

class RowBuilder {
    operator fun invoke(data: Iterable<Any?>): Row {
        TODO();
    }
}

