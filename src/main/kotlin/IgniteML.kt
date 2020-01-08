import org.apache.ignite.Ignition
import org.apache.ignite.ml.math.functions.IgniteBiFunction
import org.apache.ignite.ml.math.primitives.vector.Vector
import org.apache.ignite.ml.math.primitives.vector.VectorUtils
import org.apache.ignite.ml.selection.scoring.evaluator.Evaluator
import org.apache.ignite.ml.selection.scoring.metric.Accuracy
import org.apache.ignite.ml.tree.DecisionTreeClassificationTrainer
import org.apache.ignite.ml.tree.DecisionTreeNode


fun main1() {
    val ignite = Ignition.start()
    val dataCache = TitanicUtils.readPassengers(ignite)

    val featureExtractor: IgniteBiFunction<Int, Array<Any>, Vector> = IgniteBiFunction { _: Int?, v: Array<Any> ->
        val data = doubleArrayOf(v[0] as Double, v[5] as Double, v[6] as Double)
        data[0] = if (java.lang.Double.isNaN(data[0])) 0.0 else data[0]
        data[1] = if (java.lang.Double.isNaN(data[1])) 0.0 else data[1]
        data[2] = if (java.lang.Double.isNaN(data[2])) 0.0 else data[2]
        VectorUtils.of(*data)
    }

    val lbExtractor = IgniteBiFunction<Int, Array<Any>, Double> { _: Int?, v: Array<Any> -> v[1] as Double }

    val trainer = DecisionTreeClassificationTrainer(5, 0.0)

    val mdl: DecisionTreeNode = trainer.fit(
            ignite,
            dataCache,
            featureExtractor,  // "pclass", "sibsp", "parch"
            lbExtractor
    )
    println("\n>>> Trained model: $mdl")
    val accuracy: Double = Evaluator.evaluate(
            dataCache,
            mdl,
            featureExtractor,
            lbExtractor,
            Accuracy()
    )

    println("\n>>> Accuracy $accuracy");
}
