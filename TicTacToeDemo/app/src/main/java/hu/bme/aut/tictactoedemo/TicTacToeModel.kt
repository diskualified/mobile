package hu.bme.aut.tictactoedemo

class TicTacToeModel {
    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2
    public val FULL: Short = 3

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY))

    var nextPlayer = CROSS


    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Short) {
        model[x][y] = content
    }

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }

    fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CROSS
    }

    fun whoIsWinner() : Short {
        // empty, 1, 2, full constant (3)
        for (i in 0 .. 2) {
            if (model[i][0] == model[i][1] && model[i][1] == model[i][2]){
                if (model[i][0] == CIRCLE) {
                    return CIRCLE
                } else if (model[i][0] == CROSS) {
                    return CROSS
                }
            }
            if (model[0][i] == model[1][i] && model[1][i] == model[2][i]) {
                if (model[0][i] == CIRCLE) {
                    return CIRCLE
                } else if (model[0][i] == CROSS) {
                    return CROSS
                }
            }
        }
        if (model[0][0] == model[1][1] && model[1][1] == model[2][2]){
            if (model[0][0] == CIRCLE) {
                return CIRCLE
            } else if (model[0][0] == CROSS) {
                return CROSS
            }
        }
        if (model[2][0] == model[1][1] && model[1][1] == model[0][2]){
            if (model[2][0] == CIRCLE) {
                return CIRCLE
            } else if (model[2][0] == CROSS) {
                return CROSS
            }
        }
        var full = true
        for (i in 0 .. 2){
            for (j in 0 .. 2) {
                if (model[i][j] == EMPTY) {
                    full = false
                    break
                }
            }
        }
        if (full) {
            return FULL
        } else {
            return EMPTY
        }
    }
}