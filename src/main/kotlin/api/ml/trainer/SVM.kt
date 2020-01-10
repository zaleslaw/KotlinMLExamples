package api.ml.trainer

import api.df.DataFrame
import api.df.Row
import api.ml.Model
import api.ml.Trainer
import api.ml.Vector
import api.ml.model.SVMLinearModel

class SVM : Trainer<Double> {
    override fun fit(df: DataFrame, featureExtractor: (Row) -> Vector, labelExtractor: (Row) -> Double): Model<Double> {
        return SVMLinearModel()
    }
}
