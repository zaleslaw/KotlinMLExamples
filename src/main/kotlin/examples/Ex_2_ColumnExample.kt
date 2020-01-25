package examples

import api.createColumn
import api.df.Cell

/** DataColumn could be a basic unit for data manipulation and DataFrame building. */
fun demo_2() {


    val c1 = createColumn<String>("CountryPopulation")(listOf(140, 80, 320))
    val c2 = createColumn<Int>("CapitalPop")(listOf(13, 3, 2))

    println(c1 == c2)
    // >>> False

    (c1 + Cell(4)).show()
    // >>> |-------|
    // >>> |  140  |
    // >>> |  80   |
    // >>> |  320  |
    // >>> |   4   |
    // >>> |-------|

    val df = (c1 / 2) union (c2 * 3)
    df.where(df["CapitalPop"] gt 5).show()

    // >>>  -----------------------
    // >>> | Population | CapitalPop |
    // >>> ------------------------
    // >>> | 70  |            39   |
    // >>>  -----------------------
    // >>> | 40  |             9   |
    // >>>  -----------------------

    println(c1[1])
    // >>> 80

    println(c1 contains 140)
    // >>> True
}
