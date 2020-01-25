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
interface DataFrame {
    // Metadata
    fun schema(): DataFrameSchema

    fun schemaToString(): String

    fun columnNames(): Array<String>

    fun rowSize(): Int

    fun columnSize(): Int

    /** Returns all column names and their data types as an array. */
    fun dtypes(): Array<Pair<String, String>>

    fun <T> addColumn(column: MetaColumn)

    fun <T : Any> addColumn(column: String, col: DataColumn<Any>, function: (Cell) -> Any): DataFrame

    fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, function: (Cell, Cell) -> T): DataFrame

    fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, col2: DataColumn<Any>, function: (Cell, Cell, Cell) -> T): DataFrame

    fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, col2: DataColumn<Any>, col3: DataColumn<Any>, function: (Cell, Cell, Cell, Cell) -> T): DataFrame

    fun <T : Any> addColumn(column: String, function: (DataFrame) -> DataColumn<Any>): DataFrame

    fun <T : Any> addColumnAndFill(column: String, function: (Row) -> T): DataFrame

    fun <T : Any> addColumn(column: String, function: DataColumn<T>): DataFrame

    fun dropColumn(columnName: String)

    // Data operations
    fun col(index: Int): DataColumn<Any>

    fun col(columnName: String): DataColumn<Any>

    fun row(index: Int): Row

    // Statistic functions
    /** Returns the number of rows in the DataFrame. */
    fun count(): Long

    /** Calculates the correlation of two columns of a DataFrame. */
    fun corr(firstColumnName: String, secondColumnName: String, type: CorrelationType = CorrelationType.PEARSON)

    /** Calculates the covariance of two columns of a DataFrame. */
    fun cov(firstColumnName: String, secondColumnName: String)

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

    /** Adds/removes/checks data presented as DataColumn or throws exception on the different schemas. */
    infix operator fun plus(column: DataColumn<Any>): DataFrame

    infix operator fun minus(column: DataColumn<Any>): DataFrame

    infix operator fun contains(column: DataColumn<Any>): Boolean

    // Slicing
    // NOTE: it could be implemented with the different strategies [1..2..3] as in kotlin-numpy or via builders or via String Expressions
    //operator fun get(slicingExpression: String): DataFrame

    // NaN, NULL, Nothing handlers (NOTE: for more complex cases, please have a look to Imputer Trainer)
    /** Fills null values in the passed columns with the default values. */
    infix fun fill(defaultValues: Map<String, Cell>): DataFrame

    /** Fills null values in the passed column with the default value. */
    fun fill(columnName: String, defaultValue: Cell): DataFrame

    /**
     * Fills null values in the passed column with the default value.
     * Generates an exception if the default value is out of column type.
     */
    fun fill(columnName: String, defaultValue: Any): DataFrame

    /**
     * Fills null values in the passed column with the default value.
     * Generates an exception if the default value is out of column type.
     */
    fun fill(column: MetaColumn, defaultValue: Any): DataFrame

    /**
     * Returns a new DataFrame that drops rows containing less than maxPartOfMissedValuesInRow part of
     * null or NaN values from all values in row.
     * @maxPartOfMissedValuesInColumn Value between 0.0 and 1.0.
     */
    fun drop(maxPartOfMissedValuesInRow: Double = 0.0): DataFrame

    /** Returns a new DataFrame that drops rows containing any null or NaN values in the passed column. */
    infix fun drop(columnName: String): DataFrame

    /** Returns a new DataFrame that drops rows containing any null or NaN values in the passed column. */
    infix fun drop(column: MetaColumn): DataFrame

    /**
     * Returns a new DataFrame that drops rows containing less than maxPartOfMissedValuesInRow part of
     * null or NaN values from all values in specified columns.
     * @maxPartOfMissedValuesInColumn Value between 0.0 and 1.0.
     */
    fun drop(vararg columnNames: String, maxPartOfMissedValuesInRow: Double = 0.0): DataFrame

    // Functional operators
    /** Applies a function f to all rows. */
    fun foreach(f: (Row) -> Unit)

    fun map(f: (Row) -> Row)

    // Set theory operators
    fun distinct()

    infix fun except(other: DataFrame)

    infix fun intersect(other: DataFrame)

    infix fun union(other: DataFrame)

    // SQL operators
    fun select(vararg columns: String): DataFrame

    fun where(predicate: Predicate): DataFrame

    fun where(predicate: String): DataFrame

    fun where(indexes: BooleanArray): DataFrame


    fun orderBy(column: String, sortOrder: SortOrder = SortOrder.ASC): DataFrame

    fun groupBy(vararg columns: String): GroupedDataFrame

    /**
     * Create join relation with another DataFrame.
     *
     * NOTE: additional logic for join conditions is missed.
     */
    fun join(leftDataFrame: DataFrame, joinType: JoinType = JoinType.INNER)

    operator fun set(s: String, value: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    operator fun <T : Any> get(columnName: String): DataColumn<T>
}
