package api.ml

import api.df.Cell

/**
 * Main primitive for Machine Learning training and prediction.
 * An equivalent of a DataFrame Row.
 *
 * Could have more effective implementation for serialization needs or zipping purposes.
 */
class Vector(vararg values: Cell)
