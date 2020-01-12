package api.ml

/** De-fact, the base interface for the all models. */
interface Model<Label> {
    fun predict(vector: Vector): Label

    fun print(pretty: Boolean)
}
