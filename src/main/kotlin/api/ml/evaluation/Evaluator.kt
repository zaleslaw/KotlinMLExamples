package api.ml.evaluation

import api.df.DataFrame
import api.df.Row
import api.ml.Model
import api.ml.Vector

class Evaluator<Label>() {
    fun evaluate(df: DataFrame, model: Model<Label>, metric: Metric, featureExtractor: (Row) -> Vector, labelExtractor: (Row)->Label): Double { return 0.0}

    fun evaluate(df: DataFrame, model: Model<Label>, featureExtractor: (Row) -> Vector, labelExtractor: (Row)->Label): MetricSummary { return MetricSummary()
    }
}
