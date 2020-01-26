package api.df

import api.df.math.CorrelationType
import api.df.math.Statistics
import api.sql.JoinType
import api.sql.Predicate
import api.sql.SortOrder
import api.sql.group.GroupedDataFrame


open class ExperimentalDataFrame() : DataFrame() {
    override fun join(leftDataFrame: DataFrame, predicate: Predicate, joinType: JoinType): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val dataColumns: List<DataColumn<*>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun fill(defaultValues: List<*>): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun schema(): DataFrameSchema {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun schemaToString(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun columnNames(): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rowSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun columnSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dtypes(): Array<Pair<String, String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String, col: DataColumn<Any>, function: (Cell) -> Any): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, function: (Cell, Cell) -> T): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, col2: DataColumn<Any>, function: (Cell, Cell, Cell) -> T): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String, col: DataColumn<Any>, col1: DataColumn<Any>, col2: DataColumn<Any>, col3: DataColumn<Any>, function: (Cell, Cell, Cell, Cell) -> T): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String, function: (DataFrame) -> DataColumn<Any>): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumnAndFill(column: String, function: (Row) -> T): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any> addColumn(column: String, function: DataColumn<T>): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dropColumn(columnName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun col(index: Int): DataColumn<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun col(columnName: String): DataColumn<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun row(index: Int): Row {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(columnName: String): DataColumn<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(s: String, value: DataColumn<Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(columnName: String, rowIndex: Int): DataColumn<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(s: String, rowIndex: Int, value: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun count(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun corr(firstColumnName: String, secondColumnName: String, type: CorrelationType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cov(firstColumnName: String, secondColumnName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describe(vararg columnNames: String): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun summary(vararg statistics: Statistics) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun first(): Row {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun last(): Row {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun head(amountOfRows: Int): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tail(amountOfRows: Int): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show(amountOfRows: Int, truncate: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun split(percentage: Double): Pair<DataFrame, DataFrame> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sample(withReplacement: Boolean, fraction: Double, seed: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun plus(other: DataFrame): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun plus(row: Row): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minus(other: DataFrame): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minus(row: Row): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contains(other: DataFrame): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contains(row: Row): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun plus(column: DataColumn<Any>): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minus(column: DataColumn<Any>): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contains(column: DataColumn<Any>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(intRange: IntRange): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(intRange: IntRange, i: Int): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(i: Int, intRange: IntRange): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(intRange1: IntRange, intRange2: IntRange): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(i1: Int, i2: Int): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fill(defaultValues: Map<String, Cell>): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fill(columnName: String, defaultValue: Cell): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fill(columnName: String, defaultValue: Any): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fill(column: MetaColumn, defaultValue: Any): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drop(maxPartOfMissedValuesInRow: Double): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drop(columnName: String): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drop(column: MetaColumn): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drop(vararg columnNames: String, maxPartOfMissedValuesInRow: Double): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun foreach(f: (Row) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun map(f: (Row) -> Row) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun distinct() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun except(other: DataFrame) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun intersect(other: DataFrame) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun union(other: DataFrame) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun select(vararg columns: String): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun where(predicate: Predicate): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun where(indexes: BooleanArray): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun orderBy(column: String, sortOrder: SortOrder): DataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun groupBy(vararg columns: String): GroupedDataFrame {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun join(leftDataFrame: DataFrame, joinType: JoinType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
