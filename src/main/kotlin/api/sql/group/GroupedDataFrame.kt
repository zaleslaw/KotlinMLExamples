package api.sql.group

import api.df.DataFrame

/** The intermediate result of aggregation operators on the given DataFrame. */
interface GroupedDataFrame : DataFrame {
    fun agg(vararg aggFunctions: AggregateFunction): DataFrame
}
