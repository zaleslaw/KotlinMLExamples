package api.ml

import api.df.DataFrame
import api.df.Row

/** Base interface for all trainers. */
interface Trainer<Label> {
    fun fit(df: DataFrame, featureExtractor: (Row) -> Vector, labelExtractor: (Row) -> Label): Model<Label>
}
