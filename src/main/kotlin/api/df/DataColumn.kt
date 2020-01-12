package api.df

/**
 * Column projection with data, including meta-information.
 *
 * It supports
 */
abstract class DataColumn(data: Map<Int, Any>) {
    abstract fun metadata(): MetaColumn

    // Getters/setters/equals
    abstract operator fun get(index: Int): Cell

    abstract operator fun set(index: Int, cell: Cell)

    abstract operator fun set(index: Int, number: Number)

    abstract fun equals(other: DataColumn): Boolean

    abstract fun equals(other: DataFrame): Boolean // True if DataFrame contains one column

    // Converters
    abstract fun toDF(): DataFrame

    abstract fun toRow(): Row

    // Make operations on two DataColumns. NOTE: these methods union two columns to one DataFrame. Could be changed to algebraic operation with return type DataColumn
    abstract infix operator fun plus(other: DataColumn): DataFrame

    abstract infix operator fun minus(other: DataColumn): DataFrame

    // Make operations on DataColumn and Cell.
    abstract infix operator fun plus(other: Cell): DataColumn

    abstract infix operator fun minus(other: Cell): DataColumn

    abstract infix operator fun contains(other: Cell): Boolean

    abstract infix operator fun times(other: Cell): DataColumn

    abstract infix operator fun div(other: Cell): DataColumn

    // Make operations on DataColumn and Number.
    abstract infix operator fun plus(number: Number): DataColumn

    abstract infix operator fun minus(number: Number): DataColumn

    abstract infix operator fun times(number: Number): DataColumn

    abstract infix operator fun div(number: Number): DataColumn

    abstract infix operator fun contains(number: Number): Boolean

    // Others
    abstract fun values(): Array<Cell>

    abstract fun length(): Int

    /** Displays the DataColumn in a tabular form. */
    abstract fun show(truncate: Boolean = true)

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}
}


