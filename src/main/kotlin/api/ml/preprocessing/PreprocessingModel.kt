package api.ml.preprocessing

import api.df.DataFrame

/**
 * The common parent interface for many preprocessing models (fact learnt from dataframe for future transformation purposes)
 * like Normalization, Scaling, One-Hot Encoding, Imputing and so on.
 */
interface PreprocessingModel {
    infix fun transform(dataFrame: DataFrame): DataFrame
}
