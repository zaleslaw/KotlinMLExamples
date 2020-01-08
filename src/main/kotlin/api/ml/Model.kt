package api.ml

interface Model<Label> {
    fun predict(vector: Vector):Label
}
