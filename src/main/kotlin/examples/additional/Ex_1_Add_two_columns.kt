package examples.additional

import api.DataFrames
import api.createAndFillColumnBy
import api.df.Cell
import api.df.DataFrame
import api.df.Row
import api.sql.group.COUNT
import api.sql.group.MAX
import java.time.Year

val YEAR_OF_BIRTH = "yearOfBirth"

val AGE = "age"

val SEX = "sex"

val NAME = "name"

/* Default field name for count aggregation function. */
val COUNT = "count"

/* Default field name for max aggregation function. */
val MAX = "max"

val COMMON_TITLE = "commonTitle"

enum class Sex {
    MALE, FEMALE
}

data class Person(val name: String, val age: Int, val sex: Sex)

fun demo() {
    val dataframes = DataFrames.get()

    val persons = listOf(
            Person("John", 32, Sex.MALE),
            Person("Sarah", 69, Sex.FEMALE),
            Person("Johansen", 10, Sex.MALE),
            Person("Anastasia", 27, Sex.FEMALE)
    )

    val df = dataframes.of(persons)

    // add new column to dataFrame
    val year = Year.now().getValue()

    // First way: Java way
    var df2 = df.addColumn<Int>(YEAR_OF_BIRTH, df[AGE], { cell: Cell -> year - cell.tryDouble() })

    // Second way: More Kotlin
    df2 = df.addColumn<Int>(YEAR_OF_BIRTH, { it: DataFrame -> -it[AGE] + year }) // I don't want to extend Int type or wrap it to override minus operator

    // Third way: Try more Pandas way via overloaded basic operations
    df2 = df.addColumn(YEAR_OF_BIRTH, createAndFillColumnBy(year, df.rowSize()) - df[AGE])

    // add yet new column to dataFrame
    // First way: Java way
    val function = { ageCell: Cell, sexCell: Cell ->
        if (sexCell.value().equals(Sex.MALE)) "Mr"
        else if (ageCell.tryInt() > 30) "Mrs"
        else "Ms"
    }

    var df3 = df2.addColumn(COMMON_TITLE, df2.col(AGE), df2.col(SEX), function)

    // Second way: More Kotlin
    val function1 = { it: Row ->
        if (it[SEX].value() == Sex.MALE) "Mr"
        else if (it[AGE].tryInt() > 30) "Mrs"
        else "Ms"
    }

    df3 = df2.addColumnAndFill(COMMON_TITLE, function1)

    df3.drop(AGE)

    println(df.schemaToString())
    // >>> { name:String; sex:Sex; yearOfBirth: Int; sex: String; commonTitle: String }

    // if column 'age' was dropped
    val age = df3[YEAR_OF_BIRTH] + year

    var df4 = df3.where(age.gt(18).and(df3[SEX].equals(Sex.MALE)))

    df4.show()
    // >>>  ----------------------------------------------------
    // >>> | name     |    sex    |  yearOfBirth | commonTitle |
    // >>> -------------------------------------|---------------
    // >>> | John   |      MALE    |  1988      |      Mr      |
    // >>>  ------------------------------------|---------------

    // if column 'age' is in dataset schema
    df4 = df3.where(df3[AGE].gt(18).and(df3[SEX].equals(Sex.MALE)))

    // Prepare two data-frames for self-join
    val nameAndCount = df.groupBy(AGE, SEX, NAME).agg(COUNT())
    val maxValues = nameAndCount.groupBy(AGE, SEX).agg(MAX(COUNT))

    val predicate = maxValues[MAX].equals(nameAndCount[COUNT])
            .and(maxValues[AGE].equals(nameAndCount[AGE]))
            .and(maxValues[SEX].equals(nameAndCount[SEX]))

    nameAndCount.join(maxValues, predicate).select(AGE, SEX, NAME).show()
    // >>>  ---------------------------
    // >>> | Group<age,sex>| max(name)|
    // >>> ----------------------------
    // >>> |  <32; MALE>     |  John   |
    // >>>  ---------------------------|
    // >>> | <69; FEMALE>  |    Sarah  |
    // >>>  ---------------------------|
    // >>> | <10; MALE>    |  Johansen |
    // >>>  ---------------------------|
    // >>> | <27; FEMALE>  |  Anastasia|
    // >>>  ---------------------------|

}


