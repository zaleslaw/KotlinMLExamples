package api.ml.preprocessing

import api.df.DataFrame

/** The common parent interface for many preprocessing stages like Normalization, Scaling, One-Hot Encoding, Imputing and so on. */
interface PreprocessingTrainer {
    infix fun fit(df: DataFrame): PreprocessingModel
}
