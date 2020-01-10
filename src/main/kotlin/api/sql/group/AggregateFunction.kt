package api.sql.group

import api.df.DataFrame

interface AggregateFunction {
    fun agg(df: DataFrame): Double
}

class MAX : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class MIN : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class AVG : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class COUNT : AggregateFunction {
    override fun agg(df: DataFrame): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}



