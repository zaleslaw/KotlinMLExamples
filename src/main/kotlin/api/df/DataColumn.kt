package api.df

/** Column projection with data, including meta-information. */
abstract class DataColumn(data: Map<String, Any>) {
    operator fun get(index: Int): Cell {
        TODO("not implemented")
    }



    /** Make operations on DataColumn. */
    abstract operator fun plus(other: DataColumn): DataFrame

    abstract operator fun plus(other: Cell): DataColumn

    abstract operator fun plus(number: Number): DataColumn

    abstract operator fun minus(other: DataColumn): DataFrame

    abstract operator fun minus(other: Cell): DataColumn

    abstract operator fun minus(number: Number): DataColumn

    abstract operator fun times(number: Number): DataColumn

    abstract operator fun div(number: Number): DataColumn

    abstract operator fun contains(other: Cell): Boolean

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}
}


