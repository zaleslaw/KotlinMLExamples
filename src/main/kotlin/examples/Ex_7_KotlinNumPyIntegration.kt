package examples

import api.DataFrames
import org.jetbrains.numkt.arange
import org.jetbrains.numkt.core.reshape

/** I'd like the new numpy wrapper and a few elegant API solutions there. Of course, we should have integration point like pandas and numpy here. */
fun demo_7() {
    val ndarray = arange(15).reshape(3, 5)
    val session = DataFrames.get()

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
