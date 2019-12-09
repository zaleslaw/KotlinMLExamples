
import org.apache.spark.sql.*
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.functions.max


fun main() {
    val spark: SparkSession = getSparkSession("Spark_SQL")!!

    val dataframe = spark.range(100000000)
    println(dataframe.javaClass)
    dataframe.show(true)
    dataframe.printSchema()

    // ASSERT: Files should exists
    val stateNames = spark.read().parquet("/home/zaleslaw/data/stateNames")

// Step - 3: Task - Get most popular girl name in each state in 1945
    val filteredStateNames = stateNames
            .where("Year=1945 and Gender='F'")
            .select("Name", "State", "Count")

    filteredStateNames.cache()


    val stateAndCount = filteredStateNames
            .groupBy("state")
            .agg(max("Count").`as`("max"))

    stateAndCount.show()

// Self-join, of course
    val stateAndName = filteredStateNames
            .join(stateAndCount,
                    stateAndCount.col("max").equalTo(filteredStateNames.col("Count")).and(     stateAndCount.col("state").equalTo(filteredStateNames.col("state")))
                       )
            .select(filteredStateNames.col("state"),filteredStateNames.col("Name").alias("name")) // should choose only String names or $Columns
    .orderBy(col("state").desc(), col("Count").desc())

    stateAndName.printSchema()
    stateAndName.show()
    stateAndName.explain()
    stateAndName.explain(true)

}