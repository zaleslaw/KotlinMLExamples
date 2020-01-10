package api

import api.df.DataColumn
import api.df.DataFrame
import api.df.MetaColumn
import api.df.Row
import api.io.FileFormat
import api.io.FileOption
import api.sql.ifExistTableStrategy
import org.jetbrains.numkt.core.KtNDArray
import java.nio.file.Path
import java.sql.Connection

abstract class KotlinDataSession() {
    companion object {
        fun getOrCreate(): KotlinDataSession { return KotlinDataSession()}
    }

    // Create DataFrame methods
    abstract fun of(vararg headers: String): DataFrameBuilder

    abstract fun of(vararg columns: MetaColumn): DataFrameBuilder

    abstract fun byRows(rows: Iterable<Row>): DataFrame


    // Create Row methods
    abstract fun createRow(vararg headers: String): RowBuilder

    abstract fun createRow(vararg columns: MetaColumn): RowBuilder

    abstract fun createAndFillRow(data: Map<Any, Any>): DataFrame

    // Create DataColumn methods
    abstract fun createColumn(columnName: String): DataColumnBuilder

    abstract fun createColumn(column: MetaColumn): DataColumnBuilder

    abstract fun createAndFillColumn(data: List<Any>): DataFrame


    // IO methods
    abstract fun read(filePath: Path, fileFormat: FileFormat, vararg options: FileOption):DataFrame

    abstract fun write(filePath: Path, fileFormat: FileFormat, vararg options: FileOption):DataFrame

    // Database methods
    abstract fun readTable(tableName: String, connection: Connection, schema: String? = null, selectExpr: String):DataFrame

    abstract fun writeTable(tableName: String, connection: Connection, schema: String? = null, ifExists: ifExistTableStrategy = ifExistTableStrategy.FAIL):DataFrame

}

class DataFrameBuilder {
    operator fun invoke(vararg rows: Iterable<Any?>): DataFrame {
        TODO();
    }

    operator fun invoke(ndArray: KtNDArray<out Any>): DataFrame {
        TODO();
    }
}

class RowBuilder {
    operator fun invoke(data: Iterable<Any?>): Row {
        TODO();
    }
}

class DataColumnBuilder {
    operator fun invoke(data: Iterable<Any?>): DataColumn {
        TODO();
    }
}
