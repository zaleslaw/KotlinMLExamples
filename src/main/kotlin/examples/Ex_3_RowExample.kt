package examples

import api.KotlinDataSession
import api.df.MetaColumn

fun demo_3() {
    val session = KotlinDataSession.getOrCreate()

    val r1 = session.createRow("CountryPopulation", "CapitalPop")(listOf(140, 12))
    val r2 = session.createRow(
            MetaColumn("CountryPopulation", Int::class),
            MetaColumn("CapitalPop", Int::class)
    )(listOf(80, 4))

    println(r1 == r2)
    // >>> False

    (((r1 + 3) - 1) * 2 + r2 / 2).show()
    // >>>  -----------------------
    // >>> | Population | CapitalPop |
    // >>> ------------------------
    // >>> | 284  |            28   |
    // >>>  -----------------------
    // >>> | 40  |             2   |
    // >>>  -----------------------


    println(r1["CountryPopulation"])
    // >>> 140

    println(r1[1])
    // >>> 12

    println(r2 contains 3)
    // >>> False
}
