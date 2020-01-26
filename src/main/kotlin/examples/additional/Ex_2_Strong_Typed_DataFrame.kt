package examples.additional

import api.DataFrames
import api.defineColumn
import api.df.ExperimentalDataFrame
import api.df.Row
import java.time.Year

object df : ExperimentalDataFrame() {
    val name = defineColumn<String>("name")
    val age = defineColumn<Int>("age")
    val sex = defineColumn<Sex>("sex")
}

object df2 : ExperimentalDataFrame() {
    val name = defineColumn<String>("name")
    val age = defineColumn<Int>("age")
    val sex = defineColumn<Sex>("sex")
    val yearOfBirth = defineColumn<Int>("yearOfBirth")
}

object df3 : ExperimentalDataFrame() {
    val name = defineColumn<String>("name")
    val age = defineColumn<Int>("age")
    val sex = defineColumn<Sex>("sex")
    val yearOfBirth = defineColumn<Int>("yearOfBirth")
    val commonTitle = defineColumn<Int>("commonTitle")
}

object df4 : ExperimentalDataFrame() {
    val name = defineColumn<String>("name")
    val sex = defineColumn<Sex>("sex")
    val yearOfBirth = defineColumn<Int>("yearOfBirth")
    val commonTitle = defineColumn<Int>("commonTitle")
}

object nameAndCount : ExperimentalDataFrame() {
    val name = defineColumn<String>("name")
    val age = defineColumn<Int>("age")
    val sex = defineColumn<Sex>("sex")
    val count = defineColumn<Int>("count")
}

object maxValues : ExperimentalDataFrame() {
    val age = defineColumn<Int>("age")
    val sex = defineColumn<Sex>("sex")
    val max = defineColumn<Int>("count")
}

fun demo2() {
    val dataframes = DataFrames.get()

    val persons = listOf(
            Person("John", 32, Sex.MALE),
            Person("Sarah", 69, Sex.FEMALE),
            Person("Johansen", 10, Sex.MALE),
            Person("Anastasia", 27, Sex.FEMALE)
    )

    df.fill(persons)

    // add new column to dataFrame
    val year = Year.now().getValue()
    df2.from(df)
    df2.fillColumn(df2.yearOfBirth) { it: df2 -> -it.age + year }

    // add yet new column to dataFrame
    df3.from(df2)

    df3.fillColumnForEach(df3.commonTitle) { it: Row ->
        if (it[SEX].value() == Sex.MALE) "Mr"
        else if (it[AGE].tryInt() > 30) "Mrs"
        else "Ms"
    }

    df4.from(df3)

    // if column 'age' was dropped
    val age = df4.yearOfBirth + year

    var df5 = df4.where(age.gt(18).and(df4.sex.equals(Sex.MALE)))

    df4.show()
    // >>>  ----------------------------------------------------
    // >>> | name     |    sex    |  yearOfBirth | commonTitle |
    // >>> -------------------------------------|---------------
    // >>> | John   |      MALE    |  1988      |      Mr      |
    // >>>  ------------------------------------|---------------

    // if column 'age' is in dataset schema
    df5 = df5.where(df3.age.gt(18).and(df3.sex.equals(Sex.MALE)))

    // Prepare two data-frames for self-join
    nameAndCount.from(df.groupBy(df.age, df.sex, df.name).agg(api.sql.group.COUNT()))
    maxValues.from(nameAndCount.groupBy(nameAndCount.age, nameAndCount.sex).agg(api.sql.group.MAX(COUNT)))

    val predicate = maxValues.max.equals(nameAndCount.count)
            .and(maxValues.age.equals(nameAndCount.age))
            .and(maxValues.sex.equals(nameAndCount.sex))

    nameAndCount.join(maxValues, predicate).select(nameAndCount.age, nameAndCount.sex, nameAndCount.name).show()
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


