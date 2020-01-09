package api.df

import kotlin.reflect.KClass

/** Represents the metadata for each column. */
class MetaColumn(columnName: String, columnType: KClass<out Any> = String::class) {
}
