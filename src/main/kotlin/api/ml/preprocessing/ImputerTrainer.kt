package api.ml.preprocessing

import api.df.DataFrame

class ImputerTrainer() : PreprocessingTrainer {
    override infix fun fit(df: DataFrame): ImputingPreprocessor {
        return ImputingPreprocessor()
    }

    /** Learn imputing values for all columns. */
    fun withImputingStrategy(imputingStrategy: ImputingStrategy = ImputingStrategy.MEAN): ImputerTrainer {
        return this
    }

    /** Learn imputing values for specified columns. */
    fun withImputingStrategy(imputingStrategy: ImputingStrategy = ImputingStrategy.MEAN, vararg columnNames: String): ImputerTrainer {
        return this
    }
}
