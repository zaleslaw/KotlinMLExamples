package api.df

import api.df.math.CorrelationType
import api.df.math.Statistics
import api.sql.JoinType
import api.sql.Predicate
import api.sql.SortOrder
import api.sql.group.GroupedDataFrame


/**
 * The basic interface for all supported DataFrames.
 */
abstract class DataFrame() {
    abstract val dataColumns: List<DataColumn<*>>

    // Metadata
    abstract fun schema(): DataFrameSchema

    abstract fun schemaToString(): String

    abstract fun columnNames(): Array<String>

    abstract fun rowSize(): Int

    abstract fun columnSize(): Int

    /** Returns all column names and their data types as an array. */
    abstract fun dtypes(): Array<Pair<String, String>>

    // Change schema
    abstract fun <T : Any> addColumn(column: String)

    abstract fun <T : Any> addColumn(column: String, col: DataColumn<Any>, function: (Cell) -> Any): DataFrame

    abstract fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, function: (Cell, Cell) -> T): DataFrame

    abstract fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, col2: DataColumn<Any>, function: (Cell, Cell, Cell) -> T): DataFrame

    abstract fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, col2: DataColumn<Any>, col3: DataColumn<Any>, function: (Cell, Cell, Cell, Cell) -> T): DataFrame

    abstract fun <T : Any> addColumn(column: String, function: (DataFrame) -> DataColumn<Any>): DataFrame

    abstract fun <T : Any> addColumnAndFill(column: String, function: (Row) -> T): DataFrame

    abstract fun <T : Any> addColumn(column: String, function: DataColumn<T>): DataFrame

    abstract fun dropColumn(columnName: String)

    // Data operations
    abstract fun col(index: Int): DataColumn<Any>

    abstract fun col(columnName: String): DataColumn<Any>

    abstract fun row(index: Int): Row

    abstract operator fun get(columnName: String): DataColumn<Any>

    abstract operator fun set(s: String, value: DataColumn<Any>)

    abstract operator fun get(columnName: String, rowIndex: Int): DataColumn<Any>

    abstract operator fun set(s: String, rowIndex: Int, value: Any)

    // Statistic functions
    /** Returns the number of rows in the DataFrame. */
    abstract fun count(): Long

    /** Calculates the correlation of two columns of a DataFrame. */
    abstract fun corr(firstColumnName: String, secondColumnName: String, type: CorrelationType = CorrelationType.PEARSON)

    /** Calculates the covariance of two columns of a DataFrame. */
    abstract fun cov(firstColumnName: String, secondColumnName: String)

    /**
     * Computes basic statistics for numeric and string columns, including count, mean, stddev, min, and max.
     * If no columns are given, this function computes statistics for all numerical or string columns.
     */
    abstract fun describe(vararg columnNames: String): DataFrame

    /** Computes specified statistics for numeric and string columns. */
    abstract fun summary(vararg statistics: Statistics)

    // Quick scan and tracing
    abstract fun first(): Row

    abstract fun last(): Row

    abstract fun head(amountOfRows: Int = 5): DataFrame

    abstract fun tail(amountOfRows: Int = 5): DataFrame

    /** Displays the Dataset in a tabular form. */
    abstract fun show(amountOfRows: Int = 10, truncate: Boolean = true)

    // Split & sampling
    abstract fun split(percentage: Double): Pair<DataFrame, DataFrame>

    /**
     * Returns a new DataFrame by sampling a fraction of rows, using a user-supplied seed.
     */
    abstract fun sample(withReplacement: Boolean = false, fraction: Double = 0.1, seed: Long = 1234L)

    // Overloaded for two DataFrames or two DataFrame and one Row with the same schema
    /** Adds/removes/checks data presented as Row or DataFrame or throws exception on the different schemas. */
    abstract infix operator fun plus(other: DataFrame): DataFrame

    abstract infix operator fun plus(row: Row): DataFrame

    abstract infix operator fun minus(other: DataFrame): DataFrame

    abstract infix operator fun minus(row: Row): DataFrame

    abstract infix operator fun contains(other: DataFrame): Boolean

    abstract infix operator fun contains(row: Row): Boolean

    /** Adds/removes/checks data presented as DataColumn or throws exception on the different schemas. */
    abstract infix operator fun plus(column: DataColumn<Any>): DataFrame

    abstract infix operator fun minus(column: DataColumn<Any>): DataFrame

    abstract infix operator fun contains(column: DataColumn<Any>): Boolean

    // Slicing
    // NOTE: it could be implemented with the different strategies [1..2..3] as in kotlin-numpy
    abstract operator fun get(intRange: IntRange): DataFrame

    abstract operator fun get(intRange: IntRange, i: Int): DataFrame

    abstract operator fun get(i: Int, intRange: IntRange): DataFrame

    abstract operator fun get(intRange1: IntRange, intRange2: IntRange): DataFrame

    abstract operator fun get(i1: Int, i2: Int): DataFrame

    // NaN, NULL, Nothing handlers (NOTE: for more complex cases, please have a look to Imputer Trainer)
    /** Fills null values in the passed columns with the default values. */
    abstract infix fun fill(defaultValues: Map<String, Cell>): DataFrame

    abstract infix fun fill(defaultValues: List<*>): DataFrame

    /** Fills null values in the passed column with the default value. */
    abstract fun fill(columnName: String, defaultValue: Cell): DataFrame

    /**
     * Fills null values in the passed column with the default value.
     * Generates an exception if the default value is out of column type.
     */
    abstract fun fill(columnName: String, defaultValue: Any): DataFrame

    /**
     * Fills null values in the passed column with the default value.
     * Generates an exception if the default value is out of column type.
     */
    abstract fun fill(column: MetaColumn, defaultValue: Any): DataFrame

    /**
     * Fills all data in the given column according the given lambda.
     */
    abstract fun <DF : DataFrame> fillColumn(column: DataColumn<*>, function: (DF) -> DataColumn<*>)

    /**
     * Fills all data in the given column according the given lambda.
     */
    abstract fun <T : Any> fillColumnForEach(column: DataColumn<*>, function: (Row) -> T)

    /**
     * Returns a new DataFrame that drops rows containing less than maxPartOfMissedValuesInRow part of
     * null or NaN values from all values in row.
     * @maxPartOfMissedValuesInColumn Value between 0.0 and 1.0.
     */
    abstract fun drop(maxPartOfMissedValuesInRow: Double = 0.0): DataFrame

    /** Returns a new DataFrame that drops rows containing any null or NaN values in the passed column. */
    abstract infix fun drop(columnName: String): DataFrame

    /** Returns a new DataFrame that drops rows containing any null or NaN values in the passed column. */
    abstract infix fun drop(column: MetaColumn): DataFrame


    abstract fun drop(column: DataColumn<*>)

    abstract fun <DF : DataFrame> drop(dataFrame: DF, column: DataColumn<*>)

    /**
     * Returns a new DataFrame that drops rows containing less than maxPartOfMissedValuesInRow part of
     * null or NaN values from all values in specified columns.
     * @maxPartOfMissedValuesInColumn Value between 0.0 and 1.0.
     */
    abstract fun drop(vararg columnNames: String, maxPartOfMissedValuesInRow: Double = 0.0): DataFrame

    // Functional operators
    /** Applies a function f to all rows. */
    abstract fun foreach(f: (Row) -> Unit)

    abstract fun map(f: (Row) -> Row)

    // Set theory operators
    abstract fun distinct()

    abstract infix fun except(other: DataFrame)

    abstract infix fun intersect(other: DataFrame)

    abstract infix fun union(other: DataFrame)

    // SQL operators
    abstract fun select(vararg columns: String): DataFrame

    abstract fun select(vararg columns: DataColumn<*>): DataFrame

    abstract fun where(predicate: Predicate): DataFrame

    abstract fun where(indexes: BooleanArray): DataFrame

    abstract fun orderBy(column: String, sortOrder: SortOrder = SortOrder.ASC): DataFrame

    abstract fun groupBy(vararg columns: String): GroupedDataFrame

    abstract fun groupBy(vararg columns: DataColumn<*>): GroupedDataFrame

    /**
     * Create join relation with another DataFrame.
     *
     * NOTE: additional logic for join conditions is missed.
     */
    abstract fun join(leftDataFrame: DataFrame, joinType: JoinType = JoinType.INNER): DataFrame

    abstract fun join(leftDataFrame: DataFrame, predicate: Predicate, joinType: JoinType = JoinType.INNER): DataFrame


    /**
     * Converts one data-frame to another and populates by data from another DataFrame. Throws exception if something goes wrong.
     */
    abstract fun from(another: DataFrame)
}
