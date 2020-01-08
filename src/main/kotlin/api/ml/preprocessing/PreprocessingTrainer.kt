package api.ml.preprocessing

import api.df.DataFrame

interface PreprocessingTrainer {
    fun fit(df: DataFrame): PreprocessingModel
}
