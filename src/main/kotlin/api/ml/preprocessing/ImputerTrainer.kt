package api.ml.preprocessing

import api.df.DataFrame

class ImputerTrainer : PreprocessingTrainer {
    override fun fit(df: DataFrame): ImputingPreprocessor {
        return ImputingPreprocessor()
    }
}
