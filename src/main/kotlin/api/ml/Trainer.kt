package api.ml

import api.df.DataFrame
import api.df.Row

interface Trainer<Label> {
    fun fit(df: DataFrame, featureExtractor: (Row) -> Vector, labelExtractor: (Row) -> Label): Model<Label>
}
