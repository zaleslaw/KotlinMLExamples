package api.df

import api.sql.JoinType
import api.sql.group.GroupedDataFrame
import api.sql.Predicate
import api.sql.SortOrder

/**
 * The basic interface for all supported DataFrames.
 */
interface DataFrame {
    // Metadata
    fun schema(): DataFrameSchema

    fun schemaToString(): String

    fun columns(): Array<String>

    /** Returns all column names and their data types as an array. */
    fun dtypes(): Array<Pair<String, String>>

    fun addColumn(column: Column)

    fun dropColumn(vararg columnNames: String)

    fun col(columnName: String): Column


    // Basic stat
    /** Returns the number of rows in the DataFrame. */
    fun count(): Long

    /**
     * Computes basic statistics for numeric and string columns, including count, mean, stddev, min, and max.
     * If no columns are given, this function computes statistics for all numerical or string columns.
     */
    fun describe(vararg columnNames: String): DataFrame

    /** Computes specified statistics for numeric and string columns. */
    fun summary(vararg statistics: Statistics)


    // Quick scan and tracing
    fun first(): Row

    fun last(): Row

    fun head(amountOfRows: Int = 5): DataFrame

    fun tail(amountOfRows: Int = 5): DataFrame

    /** Displays the Dataset in a tabular form. */
    fun show(amountOfRows: Int = 10, truncate: Boolean = true)


    // Split & sampling
    fun split(percentage: Double): Pair<DataFrame, DataFrame>

    /**
     * Returns a new DataFrame by sampling a fraction of rows, using a user-supplied seed.
     */
    fun sample(withReplacement: Boolean = false, fraction: Double = 0.1, seed: Long = 1234L)



    // Overloaded for two DataFrames or two DataFrame and one Row with the same schema

    /** Adds new data to the current DataFrame or throws exception on the different schemas. */
    operator fun DataFrame.plus(other: DataFrame)

    operator fun DataFrame.plus(row: Row)

    operator fun DataFrame.minus(other: DataFrame)

    operator fun DataFrame.minus(row: Row)

    operator fun DataFrame.contains(other: DataFrame): Boolean

    operator fun DataFrame.contains(row: Row): Boolean


    // Statistic functions
    /** Calculates the correlation of two columns of a DataFrame. */
    fun corr(firstColumnName: String, secondColumnName: String, type: CorrelationType = CorrelationType.PEARSON)

    /** Calculates the covariance of two columns of a DataFrame. */
    fun cov(firstColumnName: String, secondColumnName: String)

    // NaN, NULL, Nothing handlers (NOTE: for more complex cases, please have a look to Imputer Trainer)
    // drop or fill https://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.sql.DataFrameNaFunctions

    // Functional operators
    /** Applies a function f to all rows. */
    fun foreach(f: (Row) -> Unit)

    fun map(f: (Row) -> Row)

    // Set theory operators
    fun distinct()

    fun except(other: DataFrame)

    fun intersect(other: DataFrame)

    fun union(other: DataFrame)


    // SQL operators
    fun select(vararg columns: String): DataFrame

    fun where(predicate: Predicate): DataFrame

    fun orderBy(column: String, sortOrder: SortOrder): DataFrame

    fun groupBy(vararg columns: String): GroupedDataFrame

    /**
     * Create join relation with another DataFrame.
     *
     * NOTE: additional logic for join conditions is missed.
     */
    fun join(leftDataFrame: DataFrame, joinType : JoinType = JoinType.INNER)
}
