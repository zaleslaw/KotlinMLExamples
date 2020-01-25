package examples

import api.createColumn
import api.df.Cell
import api.df.MetaColumn

/** DataColumn could be a basic unit for data manipulation and DataFrame building. */
fun demo_2() {


    val c1 = createColumn("CountryPopulation")(listOf(140, 80, 320))
    val c2 = createColumn(
            MetaColumn("CapitalPop", Int::class)
    )(listOf(13, 3, 2))

    println(c1 == c2)
    // >>> False

    (c1 + Cell(4)).show()
    // >>> |-------|
    // >>> |  140  |
    // >>> |  80   |
    // >>> |  320  |
    // >>> |   4   |
    // >>> |-------|

    (c1 / 2 + c2 * 3).where("CapitalPop > 5").show()

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
