package api.df

class Row(data: Map<String, Any>) {
    operator fun get(columnName: String): Cell {
        TODO("not implemented")
    }

    operator fun get(index: Int): Cell {
        TODO("not implemented")
    }

    fun schema(): DataFrameSchema {TODO()}

    fun values(): Array<Cell> {TODO()}

    fun length(): Int {TODO()}

    /** Displays the Row in a tabular form. */
    fun show(truncate: Boolean = true){TODO()}

    fun equals(other: Row): Boolean {TODO()}

    fun equals(other: DataFrame): Boolean {TODO()}// True if DataFrame contains one row

    // Make operations on two Rows. NOTE: these methods union two rows to one DataFrame. Could be changed to algebraic operation with return type Row
    infix operator fun plus(other: Row): DataFrame {TODO()}

    infix operator fun minus(other: Row): DataFrame {TODO()}

    // Make operations on Row and Cell.
    infix operator fun plus(other: Cell): Row {TODO()}

    infix operator fun minus(other: Cell): Row {TODO()}

    infix operator fun contains(other: Cell): Boolean {TODO()}

    infix operator fun times(other: Cell): Row {TODO()}

    infix operator fun div(other: Cell): Row {TODO()}

    // Make operations on Row and Number.
    infix operator fun plus(number: Number): Row {TODO()}

    infix operator fun minus(number: Number): Row {TODO()}

    infix operator fun times(number: Number): Row {TODO()}

    infix operator fun div(number: Number): Row {TODO()}

    infix operator fun contains(other: Number): Boolean {TODO()}

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}
}


