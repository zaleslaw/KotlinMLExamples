package api.df

/** Column projection with data, including meta-information. */
abstract class DataColumn(data: Map<Int, Any>) {
    operator fun get(index: Int): Cell {
        TODO("not implemented")
    }

    abstract fun metadata(): MetaColumn

    abstract fun values(): Array<Cell>

    abstract fun length(): Int

    /** Displays the DataColumn in a tabular form. */
    fun show(truncate: Boolean = true) {
        TODO()
    }

    abstract fun equals(other: DataColumn): Boolean

    abstract fun equals(other: DataFrame): Boolean // True if DataFrame contains one column

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

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}
}


