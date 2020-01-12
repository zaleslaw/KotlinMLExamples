package examples

import api.KotlinDataSession
import api.df.Row
import api.io.FileFormat
import api.ml.Vector
import api.ml.evaluation.BinaryClassificationMetrics
import api.ml.evaluation.Evaluator
import api.ml.preprocessing.ImputerTrainer
import api.ml.preprocessing.ImputingStrategy
import api.ml.trainer.SVM
import java.nio.file.Paths

/**
 * I'm big fun of MachineLearning and could extend this example to Cross-Validation, different preprocessors, Pipeline API,
 * hyper-parameters tuning, but finish my design exploration on this level to shortenize the initial size and amount of classes.
 */
fun demo_6() {
    val session = KotlinDataSession.getOrCreate()

    val dataframe = session.read(Paths.get("/home/titanic.csv"), FileFormat.CSV)

    val (trainDF, testDF) = dataframe.split(0.7)

    val imputerTrainer = ImputerTrainer().withImputingStrategy(ImputingStrategy.MEDIAN)

    val imputedTrainDf = imputerTrainer fit trainDF transform trainDF // Experiment

    val trainer = SVM()

    val featureExtractor: (Row) -> Vector = { row -> Vector(row["sibsp"], row["age"], row["cabin"]) }

    val labelExtractor: (Row) -> Double = { row -> row["survived"].tryDouble() }

    val mdl = trainer.fit(
            imputedTrainDf,
            featureExtractor,
            labelExtractor
    )

    val imputedTestDf = imputerTrainer.fit(testDF).transform(testDF) // Ha-ha, classic

    val accuracy: Double = Evaluator().evaluateBinaryClassification(
            imputedTestDf,
            mdl,
            BinaryClassificationMetrics.ACCURACY,
            featureExtractor,
            labelExtractor
    )

    println(accuracy)
}
