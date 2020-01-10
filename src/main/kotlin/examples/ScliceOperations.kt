package examples

import api.KotlinDataSession


fun main3() {
    val session = KotlinDataSession.getOrCreate()

    val df = session.of("Country", "Population", "Capital")(
            listOf("Russia", 145, "Moscow"),
            listOf("Germany", 80, "Berlin"),
            listOf("USA", 320, "Washington"),
            listOf("Belarus", 9, "Minsk"),
            listOf("Brazil", 200, "Brazilia")
    )
    df.show()
    // >>>  -------------------------------------
    // >>> | Country | Population |  Capital    |
    // >>> -------------------------------------|
    // >>> | Russia  |      145   |  "Moscow"   |
    // >>>  ------------------------------------|
    // >>> | Germany  |     80   |   "Berlin"   |
    // >>>  ------------------------------------|
    // >>> | USA     |      320   |  "Washington|
    // >>>  ------------------------------------|
    // >>> | Belarus  |      9   |   "Minsk"    |
    // >>>  ------------------------------------|
    // >>> | Brazil  |      200  |   "Brazilia" |
    // >>>  -------------------------------------

    df["0:2"].show()
    // >>>  -------------------------------------
    // >>> | Country | Population |  Capital    |
    // >>> -------------------------------------|
    // >>> | Russia  |      145   |  "Moscow"   |
    // >>>  ------------------------------------|
    // >>> | Germany  |     80   |   "Berlin"   |
    // >>>  ------------------------------------|
    // >>> | USA     |      320   |  "Washington|
    // >>>  ------------------------------------|

    df["1:3; 1"].show()
    // >>>  -----------
    // >>> | Country |
    // >>> ------------
    // >>> | Germany  |
    // >>>  -----------
    // >>> | USA     |
    // >>>  ------------
    // >>> | Belarus  |
    // >>>  -----------
}
