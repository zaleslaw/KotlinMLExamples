package examples

import api.KotlinDataSession
import api.df.Column
import api.io.FileFormat
import api.sql.SortOrder
import api.sql.group.MAX
import java.nio.file.Paths


fun main2() {
    val session = KotlinDataSession.getOrCreate()

    val df = session.read(Paths.get("/home/titanic.csv"), FileFormat.CSV)

    println(df.schemaToString())

    // >>> { pclass:Int; survived:Int; name: String; sex: String; age: Int ....

    df.head(2).show()

    // >>> 1;1;Barkworth, Mr. Algernon Henry Wilson;male;80;0;0;27042;30;A23;S;B;;Hessle, Yorks
    // >>> 1;0;Baumann, Mr. John D;male;;0;0;PC 17318;25,925;;S;;;New York, NY

    df.select("pclass", "age")
            .where("pclass != 1 and age > 20")
            .groupBy("pclass")
            .agg(MAX())
            .orderBy("pclass",  SortOrder.DESC)
            .show()

    // >>> 3; 47
    // >>> 2; 65


    val df2 = session.of("Country", "Population")(listOf("Russia", 145), listOf("Germany", 80), listOf("USA", 320))
    df2.addColumn(Column("Capital", String::class))
    println(df2.dtypes())
    // >>> {"Country"->String, "Population"->Int, "Capital"->String}
    df2.dropColumn("Capital")
    println(df2.dtypes())
    // >>> {"Country"->String, "Population"->Int}
}
