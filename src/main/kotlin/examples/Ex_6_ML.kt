package examples

import api.KotlinDataSession
import api.df.Row
import api.io.FileFormat
import api.ml.Vector
import api.ml.evaluation.Evaluator
import api.ml.evaluation.Metric
import api.ml.preprocessing.ImputerTrainer
import api.ml.trainer.SVM
import java.nio.file.Paths

fun demo_6() {
    val session = KotlinDataSession.getOrCreate()

    val dataframe = session.read(Paths.get("/home/titanic.csv"), FileFormat.CSV)

    val (trainDF, testDF) = dataframe.split(0.7)

    val imputedTrainDf = ImputerTrainer().fit(trainDF).transform(trainDF)

    val trainer = SVM()

    val featureExtractor: (Row) -> Vector = { row -> Vector(row["sibsp"], row["age"], row["cabin"]) }

    val labelExtractor: (Row) -> Double = { row -> row["survived"].tryDouble() }

    val mdl = trainer.fit(
            imputedTrainDf,
            featureExtractor,
            labelExtractor
    )

    val imputedTestDf = ImputerTrainer().fit(testDF).transform(testDF)

    val accuracy: Double = Evaluator<Double>().evaluate(
            imputedTestDf,
            mdl,
            Metric.ACCURACY,
            featureExtractor,
            labelExtractor
    )

    println(accuracy)
}
