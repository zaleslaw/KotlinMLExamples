package api.df

/** Could be extended for the meta-data operations and debugging purposes. */
interface DataFrameSchema {
    fun columns(): Array<MetaColumn>

    operator fun get(columnName: String): MetaColumn {
        TODO("not implemented")
    }

    operator fun get(index: Int): MetaColumn {
        TODO("not implemented")
    }
}
