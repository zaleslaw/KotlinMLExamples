package api.sql

/**
 * NOTE: I've decided not to go deeper to the widely known solution of building effective DSL for SQL-like operations.
 *
 * I suppose that the Exposed SQL https://github.com/JetBrains/Exposed could be a reference framework and API should be inherited from there.
 */
class Predicate {
    fun and(rightExpression: Predicate): Predicate {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun or(rightExpression: Predicate): Predicate {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun bool(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
