package api.ml.preprocessing

import api.df.DataFrame

interface PreprocessingModel {
    fun transform(dataFrame: DataFrame): DataFrame
}
