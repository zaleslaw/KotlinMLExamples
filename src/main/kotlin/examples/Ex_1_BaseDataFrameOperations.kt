package examples

import api.KotlinDataSession
import api.df.MetaColumn
import api.df.Row

/** This examples covers the basic functionality if DataFrame API: creation via builder, printing the metainfo, operators. */
fun demo_1() {
    val session = KotlinDataSession.getOrCreate()

    val df = session.of("Country", "Population")(listOf("Russia", 145), listOf("Germany", 80), listOf("USA", 320))
    df.addColumn(MetaColumn("Capital", String::class))

    println(df.dtypes())
    // >>> {"Country"->String, "Population"->Int, "Capital"->String}

    df.dropColumn("Capital")
    println(df.dtypes())
    // >>> {"Country"->String, "Population"->Int}


    val df1 = session.of("Country", "Population")(listOf("Belarus", 9.0), listOf("Brazil", 200.0))

    // Overloaded Operators Demo

    val df2 = df + df1
    df2.show()
    // >>>  -----------------------
    // >>> | Country | Population |
    // >>> ------------------------
    // >>> | Russia  |      145   |
    // >>>  -----------------------
    // >>> | Germany  |     80   |
    // >>>  -----------------------
    // >>> | USA     |      320   |
    // >>>  -----------------------
    // >>> | Belarus  |      9   |
    // >>>  -----------------------
    // >>> | Brazil  |      200  |
    // >>>  -----------------------

    println(df2.col("Country")[3])
    // >>> "Belarus"

    println(df2.row(1)["Population"])
    // >>> 80

    val brazilRow = Row(mapOf("Country" to "Brazil", "Population" to 200))
    val russiaRow = Row(mapOf("Country" to "Russia", "Population" to 145))

    if (russiaRow in df2 && brazilRow in df2) {
        val df3 = df2 - brazilRow - russiaRow
        df3.show()

        // >>>  -----------------------
        // >>> | Country | Population |
        // >>> ------------------------
        // >>> | Germany  |     80  |
        // >>>  -----------------------
        // >>> | USA     |      320   |
        // >>>  -----------------------
        // >>> | Belarus  |      9   |
        // >>>  -----------------------
    }
}
