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

    fun columnNames(): Array<String>

    /** Returns all column names and their data types as an array. */
    fun dtypes(): Array<Pair<String, String>>

    fun addColumn(column: MetaColumn)

    fun dropColumn(vararg columnNames: String)


    // Data operations
    fun col(index: Int): DataColumn

    fun col(columnName: String): DataColumn

    fun row(index: Int): Row

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

    /** Adds/removes/checks data presented as Row or DataFrame or throws exception on the different schemas. */
    infix operator fun plus(other: DataFrame): DataFrame

    infix operator fun plus(row: Row): DataFrame

    infix operator fun minus(other: DataFrame): DataFrame

    infix operator fun minus(row: Row): DataFrame

    infix operator fun contains(other: DataFrame): Boolean

    infix operator fun contains(row: Row): Boolean

    // Slicing
    operator fun get(slicingExpression: String): DataFrame

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

    fun where(predicate: String): DataFrame

    fun orderBy(column: String, sortOrder: SortOrder = SortOrder.ASC): DataFrame

    fun groupBy(vararg columns: String): GroupedDataFrame

    /**
     * Create join relation with another DataFrame.
     *
     * NOTE: additional logic for join conditions is missed.
     */
    fun join(leftDataFrame: DataFrame, joinType : JoinType = JoinType.INNER)

}
