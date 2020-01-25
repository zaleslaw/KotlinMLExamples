package examples

import api.DataFrames
import api.df.Cell
import api.df.MetaColumn

/**
 * The final examples lights the standard functionality of na package like in R.
 */
fun demo_8() {
    val session = DataFrames.get()

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
