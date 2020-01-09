package api.df

class Row(data: Map<String, Any>) {
    operator fun get(columnName: String): Cell {
        TODO("not implemented")
    }

    /** Make operations on Row. */
    operator fun plus(other: Row): DataFrame {
        TODO()
    }

    operator fun plus(other: Cell): Row {
        TODO()
    }

    operator fun plus(number: Number): Row {
        TODO()
    }

    operator fun minus(other: Row): DataFrame {
        TODO()
    }

    operator fun minus(other: Cell): Row {
        TODO()
    }

    operator fun minus(number: Number): Row {
        TODO()
    }

    operator fun times(number: Number): Row {
        TODO()
    }

    operator fun div(number: Number): Row {
        TODO()
    }

    operator fun contains(other: Cell): Boolean {
        TODO()
    }

    /** Applies a function f to all cells. */
    fun foreach(f: (Cell) -> Unit) {}
}


