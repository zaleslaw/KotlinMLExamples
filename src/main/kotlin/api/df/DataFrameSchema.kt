package api.df

interface DataFrameSchema {
    fun columns(): Array<MetaColumn>

    operator fun get(columnName: String): MetaColumn {
        TODO("not implemented")
    }

    operator fun get(index: Int): MetaColumn {
        TODO("not implemented")
    }
}
