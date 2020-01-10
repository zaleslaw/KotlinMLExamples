package api.sql.group

import api.df.DataFrame

interface GroupedDataFrame : DataFrame {
    fun agg(vararg aggFunctions: AggregateFunction): DataFrame
}
