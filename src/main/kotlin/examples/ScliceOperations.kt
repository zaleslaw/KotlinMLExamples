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

}
