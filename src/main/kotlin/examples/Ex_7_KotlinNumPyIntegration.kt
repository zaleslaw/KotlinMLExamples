package examples

import api.KotlinDataSession
import org.jetbrains.numkt.arange
import org.jetbrains.numkt.core.reshape

fun demo_7() {
    val ndarray = arange(15).reshape(3, 5)
    val session = KotlinDataSession.getOrCreate()

    val df = session.of("col1", "col2", "col3")(ndarray)


    println(df.schemaToString())
    // >>> { col1:Int; col2:Int; col3: Int;

    println(df.count())
    // >>> 3

    df.head(2).show()

    // >>>  -------------------------------------
    // >>> | col1     | col2      |  col3       |
    // >>> -------------------------------------|
    // >>> | 1       |      2    |  3           |
    // >>>  ------------------------------------|
    // >>> | 4       |     5     |   6          |
    // >>>  ------------------------------------|
}
