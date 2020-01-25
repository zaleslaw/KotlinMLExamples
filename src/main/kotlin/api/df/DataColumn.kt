package api.df

import api.sql.Predicate

/**
 * Column projection with data, including meta-information.
 */
abstract class DataColumn<T : Any>(data: Map<Int, Any>) {
    abstract fun metadata(): MetaColumn

    // Getters/setters/equals
    abstract operator fun get(index: Int): Cell

    abstract operator fun set(index: Int, cell: Cell)

    abstract operator fun set(index: Int, value: Any)

    abstract fun equals(other: Any): Predicate

    abstract fun equals(other: DataColumn<T>): Predicate

    abstract fun equals(other: DataFrame): Predicate

    abstract fun notEquals(other: Any): Predicate

    abstract fun notEquals(other: DataColumn<T>): Predicate

    // Converters
    abstract fun toDF(): DataFrame

    abstract fun toRow(): Row

    // Set theory operators
    abstract infix fun union(other: DataColumn<Any>): DataFrame

    // Make operations on two DataColumns. NOTE: these methods union two columns to one DataFrame. Could be changed to algebraic operation with return type DataColumn
    abstract infix operator fun plus(other: DataColumn<Any>): DataColumn<Any>

    abstract infix operator fun minus(other: DataColumn<Any>): DataColumn<Any>

    abstract operator fun unaryMinus(): DataColumn<Any>

    // Make operations on DataColumn and Cell.
    abstract infix operator fun plus(other: Cell): DataColumn<T>

    abstract infix operator fun minus(other: Cell): DataColumn<T>

    abstract infix operator fun contains(other: Cell): Boolean

    abstract infix operator fun times(other: Cell): DataColumn<T>

    abstract infix operator fun div(other: Cell): DataColumn<T>

    // Make operations on DataColumn and Number.
    abstract infix operator fun plus(number: Number): DataColumn<T>

    abstract infix operator fun minus(number: Number): DataColumn<T>

    abstract infix operator fun times(number: Number): DataColumn<Any>

    abstract infix operator fun div(number: Number): DataColumn<T>

    abstract infix operator fun contains(number: Number): Boolean

    // Compare data column and Number
    abstract infix fun gt(number: Number): Predicate

    // Others
    abstract fun values(): Array<Cell>

    abstract fun length(): Int

    /** Displays the DataColumn in a tabular form. */
    abstract fun show(truncate: Boolean = true)

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}


}


