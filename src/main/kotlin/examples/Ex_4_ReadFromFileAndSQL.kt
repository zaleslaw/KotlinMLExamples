package examples

import api.DataFrames
import api.io.FileFormat
import api.sql.SortOrder
import api.sql.group.MAX
import java.nio.file.Paths

val PCLASS = "pclass"

val AGE = "age"

/** This examples demonstrates the power the easy API of ETL-like process over the DataFrame. */
fun demo_4() {
    val session = DataFrames.get()

    val df = session.read(Paths.get("/home/titanic.csv"), FileFormat.CSV)

    println(df.schemaToString())
    // >>> { pclass:Int; survived:Int; name: String; sex: String; age: Int ....

    println(df.count())
    // >>> 1502

    df.head(2).show()

    // >>> 1;1;Barkworth, Mr. Algernon Henry Wilson;male;80;0;0;27042;30;A23;S;B;;Hessle, Yorks
    // >>> 1;0;Baumann, Mr. John D;male;;0;0;PC 17318;25,925;;S;;;New York, NY


    df.select(PCLASS, AGE)
            .where(df[PCLASS].notEquals(1).and(df[AGE] gt 20))
            .groupBy(PCLASS)
            .agg(MAX())
            .orderBy(PCLASS, SortOrder.DESC)
            .show()

    // >>> 3; 47
    // >>> 2; 65
}
