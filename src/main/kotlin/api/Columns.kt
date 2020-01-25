package api

import api.df.DataColumn
import api.df.DataFrame
import api.df.MetaColumn

// Create DataColumn methods
fun <T : Any> createColumn(columnName: String): DataColumnBuilder<T> {
    TODO()
}

fun <T : Any> createColumn(column: MetaColumn): DataColumnBuilder<T> {
    TODO()
}

fun <T : Any> createAndFillColumnBy(value: T, rowSize: Int): DataColumn<T> {
    TODO()
}

fun createAndFillColumnBy(data: List<Any>): DataFrame {
    TODO()
}

class DataColumnBuilder<T : Any> {
    operator fun invoke(data: Iterable<Any?>): DataColumn<T> {
        TODO();
    }
}
