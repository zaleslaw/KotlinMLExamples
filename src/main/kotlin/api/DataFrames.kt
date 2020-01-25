package api

import api.df.DataFrame
import api.df.MetaColumn
import api.io.FileFormat
import api.io.FileOption
import api.sql.IfExistTableStrategy
import org.jetbrains.numkt.core.KtNDArray
import java.nio.file.Path
import java.sql.Connection

abstract class DataFrames() {
    companion object {
        fun get(): DataFrames {
            return DataFrames()
        }
    }

    // Create DataFrame methods
    abstract fun of(vararg headers: String): DataFrameBuilder

    abstract fun of(vararg columns: MetaColumn): DataFrameBuilder

    abstract fun <T> of(persons: List<T>): DataFrame

    // IO methods
    abstract fun read(filePath: Path, fileFormat: FileFormat, vararg options: FileOption): DataFrame

    abstract fun write(filePath: Path, fileFormat: FileFormat, vararg options: FileOption): DataFrame

    // Database methods
    abstract fun readTable(tableName: String, connection: Connection, schema: String? = null, selectExpr: String): DataFrame

    abstract fun writeTable(tableName: String, connection: Connection, schema: String? = null, ifExists: IfExistTableStrategy = IfExistTableStrategy.FAIL): DataFrame
}

class DataFrameBuilder {
    operator fun invoke(vararg rows: Iterable<Any?>): DataFrame {
        TODO();
    }

    operator fun invoke(ndArray: KtNDArray<out Any>): DataFrame {
        TODO();
    }
}


