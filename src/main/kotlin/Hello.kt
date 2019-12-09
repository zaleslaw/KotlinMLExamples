import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.sql.*
import org.apache.spark.sql.functions.avg
import org.apache.spark.sql.types.DataTypes
import org.apache.spark.sql.types.DataTypes.IntegerType
import org.apache.spark.sql.types.DataTypes.StringType
import java.util.*


fun main() {
    val spark: SparkSession? = getSparkSession("Spark_SQL")

    val sc = spark?.sparkContext()
    val jsc = JavaSparkContext.fromSparkContext(sc)

    val salariesData: List<String> = Arrays.asList(
            "John 1900 January",
            "Mary 2000 January",
            "John 1800 February",
            "John 1000 March",
            "Mary 1500 February",
            "Mary 2900 March"
    )

    val salaries = jsc.parallelize(salariesData, 3).map { s: String -> s.split(" ".toRegex()).toTypedArray() }
    val rowRdd: JavaRDD<Row> = salaries.map<Row> { x: Array<String> -> RowFactory.create(x[0], Integer.valueOf(x[1])) }


    rowRdd.toDebugString()
    rowRdd.collect().forEach(System.out::println)

    val fields = arrayOf(
            DataTypes.createStructField("name", StringType, true),
            DataTypes.createStructField("amount", IntegerType, true))

    val salarySchema = DataTypes.createStructType(fields)

    val df: Dataset<Row> = SQLContext(jsc).createDataFrame(rowRdd, salarySchema)

    df.groupBy("name")
            .agg(avg("amount"))
            .show()

}

fun getSparkSession(appName: String?): SparkSession? { //For windows only: don't forget to put winutils.exe to c:/bin folder
    System.setProperty("hadoop.home.dir", "c:\\")
    val spark = SparkSession.builder()
            .master("local[2]")
            .appName(appName)
            .orCreate
    spark.sparkContext().setLogLevel("ERROR")
    return spark
}