package api.df

abstract class Row {
    operator fun get(columnName: String): Cell {
        TODO("not implemented")
    }

    /** Applies a function f to all cells. */
    abstract fun foreach(f: (Cell) -> Unit)
}
