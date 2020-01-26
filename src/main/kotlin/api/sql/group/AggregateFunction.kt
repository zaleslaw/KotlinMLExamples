package api.sql.group

import api.df.DataFrame

interface AggregateFunction {
    fun agg(df: DataFrame): Double
}

class MAX(vararg fields: String) : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class MIN(vararg fields: String) : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class AVG(vararg fields: String) : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class COUNT : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}



