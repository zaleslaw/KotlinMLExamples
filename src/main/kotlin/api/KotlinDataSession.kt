package api

import api.df.Column
import api.df.DataFrame
import api.df.Row
import api.io.FileFormat
import api.io.FileOption
import api.sql.ifExistTableStrategy
import java.nio.file.Path
import java.sql.Connection

abstract class KotlinDataSession() {
    companion object {
        fun getOrCreate(): KotlinDataSession { return KotlinDataSession()}
    }

    // Create DataFrame methods
    abstract fun of(vararg headers: String): DataFrameBuilder

    abstract fun of(vararg columns: Column): DataFrameBuilder

    abstract fun byRows(rows: Iterable<Row>): DataFrame


    // IO methods
    abstract fun read(filePath: Path, fileFormat: FileFormat, vararg options: FileOption):DataFrame

    abstract fun write(filePath: Path, fileFormat: FileFormat, vararg options: FileOption):DataFrame

    // Database methods
    abstract fun readTable(tableName: String, connection: Connection, schema: String? = null, selectExpr: String):DataFrame

    abstract fun writeTable(tableName: String, connection: Connection, schema: String? = null, ifExists: ifExistTableStrategy = ifExistTableStrategy.FAIL):DataFrame

    // Generators


}

class DataFrameBuilder {
    operator fun invoke(vararg rows: Iterable<Any?>): DataFrame {
        TODO();
    }
}
