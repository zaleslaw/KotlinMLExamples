package examples

import api.KotlinDataSession
import api.df.Cell
import api.df.MetaColumn

fun main5() {
    val session = KotlinDataSession.getOrCreate()

    val c1 = session.createColumn("CountryPopulation")(listOf(140, 80, 320))
    val c2 = session.createColumn(
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

    (c1/2 + c2*3).where("CapitalPop > 5").show()

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
