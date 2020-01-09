package api.df

import kotlin.reflect.KClass

/** Represents the metadata for each column. */
class Column(columnName: String, columnType: KClass<out Any> = String::class) {
}
