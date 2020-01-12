package examples

import api.KotlinDataSession
import api.df.Cell
import api.df.MetaColumn

fun demo_8() {
    val session = KotlinDataSession.getOrCreate()

    val df = session.of("Country", "Population")(listOf("", 145.0), listOf("Germany", 80.0), listOf("USA", Double.NaN))
    df.show()
    // >>>  -----------------------
    // >>> | Country | Population |
    // >>> ------------------------
    // >>> |     NaN   |   145.0   |
    // >>>  -----------------------
    // >>> | Germany  |     80.0   |
    // >>>  -----------------------
    // >>> | USA     |      NaN    |
    // >>>  -----------------------

    df.drop().show()
    // >>>  -----------------------
    // >>> | Country | Population |
    // >>>  -----------------------
    // >>> | Germany  |     80.0   |
    // >>>  -----------------------

    df.drop("Country").show()
    // >>>  -----------------------
    // >>> | Country | Population |
    // >>> ------------------------
    // >>> | Germany  |     80.0  |
    // >>>  -----------------------
    // >>> | USA     |      NaN   |
    // >>>  -----------------------

    df.fill(MetaColumn("Population", Double::class), 100.0).drop()
    // >>>  -----------------------
    // >>> | Country | Population |
    // >>>  -----------------------
    // >>> | Germany  |     80.0   |
    // >>>  -----------------------
    // >>> | USA     |      100.0   |
    // >>>  -----------------------

    df.fill(mapOf("Country" to Cell("DefaultCountry"), "Population" to Cell(100.0))).drop()

    // >>>  -----------------------
    // >>> | Country | Population |
    // >>> ------------------------
    // >>> | Default...|  145.0   |
    // >>>  -----------------------
    // >>> | Germany  |     80.0   |
    // >>>  -----------------------
    // >>> | USA     |      100.0    |
    // >>>  -----------------------

}
