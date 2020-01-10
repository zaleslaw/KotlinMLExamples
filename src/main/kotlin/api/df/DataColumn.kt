package api.df

/** Column projection with data, including meta-information. */
abstract class DataColumn(data: Map<String, Any>) {
    operator fun get(index: Int): Cell {
        TODO("not implemented")
    }

    /** Make operations on two DataColumns. */
    abstract infix operator fun plus(other: DataColumn): DataFrame

    abstract infix operator fun plus(other: Cell): DataColumn

    abstract infix operator fun plus(number: Number): DataColumn

    abstract infix operator fun minus(other: DataColumn): DataFrame

    abstract infix operator fun minus(other: Cell): DataColumn

    abstract infix operator fun minus(number: Number): DataColumn

    abstract infix operator fun times(number: Number): DataColumn

    abstract infix operator fun div(number: Number): DataColumn

    abstract infix operator fun contains(other: Cell): Boolean

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}
}


