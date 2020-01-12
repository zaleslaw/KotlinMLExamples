package api.ml.evaluation

import api.df.DataFrame
import api.df.Row
import api.ml.Model
import api.ml.Vector

/**
 * Calculates the given metric (regression or binary classification)
 * over the whole DataFrame with special passed converters the initial data to the special math form e.g. Vector.
 *
 * Parametrized by <Label> - the special type of the label for the supervised classification task.
 */
class Evaluator() {
    fun <Label> evaluateBinaryClassification(df: DataFrame, model: Model<Label>, metric: BinaryClassificationMetrics, featureExtractor: (Row) -> Vector, labelExtractor: (Row) -> Label): Double {
        return 0.0
    }

    fun <Label> evaluateBinaryClassification(df: DataFrame, model: Model<Label>, featureExtractor: (Row) -> Vector, labelExtractor: (Row) -> Label): BinaryClassificationMetricSummary {
        return BinaryClassificationMetricSummary()
    }

    fun evaluateRegression(df: DataFrame, model: Model<Double>, featureExtractor: (Row) -> Vector): RegressionMetricSummary {
        return RegressionMetricSummary()
    }
}
