package api

import api.df.DataFrame
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
    abstract fun of(): DataFrame


    // IO methods
    abstract fun read(filePath: Path, fileFormat: FileFormat, vararg options: FileOption):DataFrame

    abstract fun write(filePath: Path, fileFormat: FileFormat, vararg options: FileOption):DataFrame

    // Database methods
    abstract fun readTable(tableName: String, connection: Connection, schema: String? = null, selectExpr: String):DataFrame

    abstract fun writeTable(tableName: String, connection: Connection, schema: String? = null, ifExists: ifExistTableStrategy = ifExistTableStrategy.FAIL):DataFrame

    // Generators
}
