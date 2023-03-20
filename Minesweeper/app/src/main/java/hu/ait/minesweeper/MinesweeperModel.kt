package hu.ait.minesweeper

import android.util.Log

class MinesweeperModel {
    public val EMPTY: Short = 0
    public val MINE: Short = 1
    public val EMPTY_COVER: Short = 2
    public val MINE_COVER: Short = 3
    public val FLAG: Short = 5
    public val REVEAL: Short = 6

    var currMode = REVEAL
    var remainingMines = 3

    private var model = arrayOf(
        shortArrayOf(EMPTY_COVER, EMPTY_COVER, EMPTY_COVER, EMPTY_COVER, EMPTY_COVER),
        shortArrayOf(EMPTY_COVER, EMPTY_COVER, MINE_COVER, EMPTY_COVER, EMPTY_COVER),
        shortArrayOf(EMPTY_COVER, EMPTY_COVER, EMPTY_COVER, EMPTY_COVER, EMPTY_COVER),
        shortArrayOf(EMPTY_COVER, MINE_COVER, EMPTY_COVER, EMPTY_COVER, EMPTY_COVER),
        shortArrayOf(EMPTY_COVER, EMPTY_COVER, EMPTY_COVER, MINE_COVER, EMPTY_COVER))

    fun switchMode() {
        currMode = if (currMode == REVEAL) FLAG else REVEAL
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, mode: Short) : Int {
        if (mode == FLAG) {
            if (getFieldContent(x, y) == MINE_COVER) {
                model[x][y] = FLAG
                remainingMines -= 1
            } else if (getFieldContent(x, y) == FLAG) {
                model[x][y] = MINE_COVER
                remainingMines += 1
            } else if (remainingMines == 0 && getFieldContent(x,y) == EMPTY_COVER) {
                remainingMines -= 1
            }
        }
        if (!(mode == FLAG && (getFieldContent(x, y) == FLAG || getFieldContent(x, y) == MINE_COVER || getFieldContent(x, y) == EMPTY))) {
            model[x][y] = (model[x][y] - 2).toShort()
            if (getFieldContent(x, y) == EMPTY && calcNeighbors(x, y) == 0) {
                recReveal(x,y)
            }
        }
        return remainingMines
    }

    fun calcNeighbors(x: Int, y: Int) : Int {
        var count = 0
        for (i in -1 .. 1) {
            for (j in -1 .. 1) {
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 4 && y + j >= 0 && y + j <= 4) {
                    if (getFieldContent(x+i, y+j) == MINE || getFieldContent(x+i, y+j) == MINE_COVER || getFieldContent(x+i, y+j) == FLAG) {
                        count += 1
                    }
                }
            }
        }
        return count
    }

    fun resetModel() {
        var set = mutableSetOf<Int>()
        while (set.size < 3) {
            set.add((0..24).random())
        }

        for (i in 0..4) {
            for (j in 0..4) {
                model[i][j] = EMPTY_COVER
            }
        }
        for (elt in set) {
            model[elt/5][elt%5] = MINE_COVER
        }
        currMode = REVEAL
        remainingMines = set.size
    }
    fun recReveal(i: Int, j: Int) {
        for (x in -1 .. 1) {
            for (y in -1 .. 1) {
                if (!(x == 0 && y == 0) && x + i >= 0 && x + i <= 4 && y + j >= 0 && y + j <= 4) {

                    if (getFieldContent(x+i, y+j) == EMPTY_COVER) {
                        setFieldContent(x + i, y + j, REVEAL)
                        if (calcNeighbors(x + i, y + j) == 0) {
                            recReveal(x + i, y + j)
                        }
                    }
                }
            }
        }
    }
}