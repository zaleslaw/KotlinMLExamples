package api.ml.evaluation

/**
 * Represent all metrics that could be calculated for the given model on the fixed train dataframe.
 *
 * Calculates all metrics presented in @see RegressionMetrics.class.
 */
class RegressionMetricSummary {
    operator fun get(metricsValue: BinaryClassificationMetrics): Double {
        TODO()
    }
}
